package com.tcc.secretaria.providers;

import com.google.gson.Gson;
import com.tcc.secretaria.DTO.*;
import com.tcc.secretaria.Repositories.*;
import com.tcc.secretaria.database.*;
import com.tcc.secretaria.mapper.AlunoMapper;
import com.tcc.secretaria.mapper.DisciplinaMapper;
import com.tcc.secretaria.mapper.ProfessorMapper;
import com.tcc.secretaria.mapper.SecretariaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*======================================================================================================================
||Classe responsavel por receber as requisições referentes ao modulo de Secretaria. Estas requisições são tratadas e  ||
||seus resultados são retorados ao frontend para que o mesmo possa manipula-los.                                      ||
======================================================================================================================*/
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SecretariaProvider {
    Gson gson = new Gson();

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    AluDisRepository aluDisRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    SecretariaRepository secretariaRepository;

    @Autowired
    AluAtividadeRepository aluAtividadeRepository;

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    ArquivoRepository arquivoRepository;

    //==Disciplinas=====================================================================================================

    @GetMapping(value = "/getDisciplinas")
    public @ResponseBody String getDisciplinas(){
        List<Disciplina> list;
        list = disciplinaRepository.findAll();
        return gson.toJson(DisciplinaMapper.ListEntitytoListDTO(list));
    }

    @GetMapping(value = "/searchDisciplinas/{name}")
    public @ResponseBody String searchDisciplinas(@PathVariable String name){
        List<Disciplina> list = disciplinaRepository.searchDisciplina(name);
        return gson.toJson(DisciplinaMapper.ListEntitytoListDTO(list));
    }

    @GetMapping("/getDisciplinaById/{id}")
    public @ResponseBody String getDisciplinaById(@PathVariable Long id) {
        DisciplinaDTO disciplinaDTO = DisciplinaMapper.EntitytoDTO(disciplinaRepository.getOne(id));
        List<Aluno> alunos = new ArrayList<>();
        List<AluDis> aludisList= aluDisRepository.getAluDisByIdDisciplina(id);

        for(AluDis aluDis: aludisList){
            alunos.add(aluDis.getAlunofk());
        }

        disciplinaDTO.setLs_alunos((ArrayList<AlunoListDTO>) AlunoMapper.ListEntitytoListDTO(alunos));


        return gson.toJson(disciplinaDTO);
    }

    @PutMapping(path="/updateDisciplina",  consumes = "application/json", produces = "application/json")
    public @ResponseBody String updateDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) throws ParseException {
        List<AluDis> aluDisAntigos = aluDisRepository.getAluDisByIdDisciplina(disciplinaDTO.getId_disciplina());
        DisciplinaMapper.DTOtoEntity(disciplinaDTO);
        Professor professor = professorRepository.getOne(disciplinaDTO.getId_professor());
        disciplinaRepository.updateDisciplina(professor, professor.getNome(), disciplinaDTO.getSt_nome(),disciplinaDTO.getNu_carga_horaria(),disciplinaDTO.getTx_descricao(),disciplinaDTO.getUrl_img(),disciplinaDTO.getId_disciplina());

        int id = -1;
        for(AluDis aluDis: aluDisAntigos){
            for(int i = 0; i < disciplinaDTO.getLs_alunos().size(); i++){
                if(aluDis.getAlunofk().getId() == disciplinaDTO.getLs_alunos().get(i).getId_aluno()){
                    id = i;
                    break;
                }
            }
            if(id >=0){
                disciplinaDTO.getLs_alunos().remove(id);
            }else{
                aluDisRepository.deleteByIdAluno(aluDis.getAlunofk().getId());
                aluAtividadeRepository.deleteAtividadeByIdAlunoAndIdDisciplina(aluDis.getAlunofk().getId(),disciplinaDTO.getId_disciplina());
            }
            id = -1;
        }

        for(AlunoListDTO alunoListDTO: disciplinaDTO.getLs_alunos()){
            aluDisRepository.save(new AluDis(disciplinaRepository.getOne(disciplinaDTO.getId_disciplina()),alunoRepository.getOne(alunoListDTO.getId_aluno()),0));
            List<Atividade> atividades = atividadeRepository.getAtividadeByIdDisciplina(disciplinaDTO.getId_disciplina());

            for(Atividade atividade: atividades){
                aluAtividadeRepository.save(new AluAtividade(disciplinaRepository.getOne(disciplinaDTO.getId_disciplina()),alunoRepository.getOne(alunoListDTO.getId_aluno()),atividade,-1,false,""));
            }

        }
        return gson.toJson(true);
    }

    @PostMapping(path="/saveDisciplina",  consumes = "application/json", produces = "application/json")
    public String createDisciplna (@RequestBody DisciplinaDTO disciplinaDTO){
        Disciplina d = DisciplinaMapper.DTOtoEntity(disciplinaDTO);
        Disciplina d2 = disciplinaRepository.save(d);

        for(AlunoListDTO aluno : disciplinaDTO.getLs_alunos()){
            Aluno a = new Aluno();
            a.setId(aluno.getId_aluno());
            aluDisRepository.save(new AluDis(d2,a,0));
        }

        return gson.toJson(disciplinaDTO);
    }

    @DeleteMapping("/deleteDisciplina/{id}")
    public String deleteDisciplina(@PathVariable Long id) {
        aluDisRepository.deleteByIdDisciplina(id);
        aluAtividadeRepository.deleteAtividadeByIdDisciplina(id);
        atividadeRepository.deleteAtividadeByIdDisciplina(id);
        arquivoRepository.deleteArquivosByIdDisciplina(id);
        disciplinaRepository.deleteById(id);
        return gson.toJson(id);
    }

    //==Alunos==========================================================================================================

    @GetMapping(value = "/getAlunos")
    public @ResponseBody String getAlunos(){
        List<Aluno> list;
        list = alunoRepository.findAll();
        return gson.toJson(AlunoMapper.ListEntitytoListDTO(list));
    }

    @GetMapping(value = "/searchAlunos/{name}")
    public @ResponseBody String searchAlunos(@PathVariable String name){
        List<Aluno> list = alunoRepository.searchAluno(name);
        return gson.toJson(AlunoMapper.ListEntitytoListDTO(list));

    }

    @GetMapping("/getAlunoById/{id}")
    public @ResponseBody String getAlunoById(@PathVariable Long id) {
        Aluno a = alunoRepository.getOne(id);
        return gson.toJson(AlunoMapper.EntitytoDTO(a));

    }

    @PutMapping(path="/updateAluno",  consumes = "application/json", produces = "application/json")
    public @ResponseBody String updateAluno(@RequestBody AlunoDTO alunoDTO){
        StringTokenizer st = new StringTokenizer(alunoDTO.getDt_data_nasc(),"-");
        int year = Integer.parseInt(st.nextToken());
        int mouth = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        LocalDate localDate = LocalDate.of(year,mouth,day);
        alunoRepository.updateAluno(alunoDTO.getCo_telefone(),alunoDTO.getDc_rg(),alunoDTO.getSt_nome_aluno(),alunoDTO.getSt_endereco(),alunoDTO.getCo_email(),localDate,alunoDTO.getDc_cpf(),alunoDTO.getSt_nome_mae(),alunoDTO.getSt_nome_pai(),alunoDTO.getId_aluno());
        return gson.toJson(true);
    }

    @PostMapping(path="/saveAluno",  consumes = "application/json", produces = "application/json")
    public String createAluno(@RequestBody AlunoDTO alunoDTO){
        if(alunoRepository.getExistAlunoWithRGorCPF(alunoDTO.getDc_cpf(),alunoDTO.getDc_rg())>0){
            AlunoDTO alunoDTO1 = new AlunoDTO();
            alunoDTO1.setId_aluno(-1L);
            return gson.toJson(alunoDTO1);
        }else {
            Aluno a = AlunoMapper.DTOtoEntity(alunoDTO);
            loginRepository.save(new Login(alunoDTO.getDc_cpf(), alunoDTO.getPw_senha_aluno(), 1));
            return gson.toJson(alunoRepository.save(a));
        }
    }

    @DeleteMapping("/deleteAluno/{id}")
    public String deleteAluno(@PathVariable Long id) {
        loginRepository.deleteLoginByCode(alunoRepository.getOne(id).getCpf());
        aluDisRepository.deleteByIdAluno(id);
        alunoRepository.deleteById(id);
        return gson.toJson(id);
    }

    //==Professores=====================================================================================================

    @GetMapping(value = "/getProfessores")
    public @ResponseBody String getProfessores(){
        List<Professor> list;
        list = professorRepository.findAll();
        return gson.toJson(ProfessorMapper.ListEntitytoListDTO(list));
    }

    @GetMapping(value = "/searchProfessores/{name}")
    public @ResponseBody String searchProfessores(@PathVariable String name){
        List<Professor> list = professorRepository.searchProfessor(name);
        return gson.toJson(ProfessorMapper.ListEntitytoListDTO(list));
    }

    @GetMapping("/getProfessorById/{id}")
    public @ResponseBody String getProfessorById(@PathVariable Long id) {

        Professor p = professorRepository.getOne(id);
        return gson.toJson(ProfessorMapper.EntitytoDTO(p));

    }

    @PutMapping(path="/updateProfessor",  consumes = "application/json", produces = "application/json")
    public @ResponseBody String updateProfessor(@RequestBody ProfessorDTO professorDTO) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        professorRepository.updateProfessor(professorDTO.getdc_cpf(),sdf.parse(professorDTO.getDt_data_nasc()), professorDTO.getCo_email(),professorDTO.getSt_endereco(),professorDTO.getSt_nome_professor(),professorDTO.getdc_rg(),professorDTO.getCo_telefone(),professorDTO.getId_professor());
        return gson.toJson(true);
    }

    @PostMapping(path="/saveProfessor",  consumes = "application/json", produces = "application/json")
    public String createProfessor(@RequestBody ProfessorDTO professorDTO) throws ParseException {
        if(professorRepository.getExistProfessorWithRGorCPF(professorDTO.getdc_cpf(),professorDTO.getdc_rg())>0){
            ProfessorDTO professorDTO1 = new ProfessorDTO();
            professorDTO1.setId_professor(-1L);
            return gson.toJson(professorDTO1);
        }else {
            Professor p = ProfessorMapper.DTOtoEntity(professorDTO);
            loginRepository.save(new Login(professorDTO.getdc_cpf(), professorDTO.getPw_senha_prof(), 2));
            return gson.toJson(professorRepository.save(p));
        }
    }

    @DeleteMapping("/deleteProfessor/{id}")
    public String deleteProfessor(@PathVariable Long id) {

        if(disciplinaRepository.countDisciplinaByProfessor(id) > 0){
            return gson.toJson(-1L);
        }else {
            loginRepository.deleteLoginByCode(professorRepository.getOne(id).getCpf());
            professorRepository.deleteById(id);
            return gson.toJson(id);
        }
    }

    //==Secretaria======================================================================================================

    @GetMapping("/getSecretariaByCode/{id}")
    public @ResponseBody String getSecretariaByCode(@PathVariable String id) {
        return gson.toJson(SecretariaMapper.EntitytoDTO(secretariaRepository.getSecretariaByCode(id)));
    }

}
