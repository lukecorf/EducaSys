package com.tcc.professor.providers;

import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tcc.professor.DTO.*;
import com.tcc.professor.Repositories.*;
import com.tcc.professor.database.*;
import com.tcc.professor.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProfessorProvider {
    private Gson gson = new Gson();

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private AluDisRepository aluDisRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AluAtividadeRepository aluAtividadeRepository;


    @GetMapping(value = "/getDisciplinas/{id}")
    public @ResponseBody
    String getDisciplinas(@PathVariable Long id){

        List<Disciplina> disciplinas = disciplinaRepository.getDisciplinaByIdProfesor(id);

        return gson.toJson(DisciplinaMapper.ListEntitytoListADTO(disciplinas));
    }

    @GetMapping("/getProfessorByCode/{id}")
    public @ResponseBody String getSecretariaByCode(@PathVariable String id) {
        return gson.toJson(ProfessorMapper.EntitytoDTO(professorRepository.getProfessorByCode(id)));
    }

    @GetMapping("/getDisciplinaById/{id}")
    public @ResponseBody String getDisciplinaById(@PathVariable Long id) {
        return gson.toJson(DisciplinaMapper.EntitytoDTO(disciplinaRepository.getOne(id)));
    }

    @GetMapping("/getAtividadeEntregues/{ida}/{idd}")
    public @ResponseBody String getAtividadesEntregues(@PathVariable Long ida, @PathVariable Long idd){
        List<AluAtividade> laa = aluAtividadeRepository.getAtividadesEntregues(idd,ida);

        if(laa.size()>0) {
            List<Long> id = new ArrayList<>();

            for (AluAtividade a : laa) {
                id.add(a.getAlunofk().getId());
            }

            List<String> nome = alunoRepository.getNomesAlunosByListId(id);

            List<AluAtividadeDTO> aluAtividadeDTO = new ArrayList<>();

            for (int i = 0; i < laa.size(); i++) {
                aluAtividadeDTO.add(new AluAtividadeDTO(nome.get(i), laa.get(i).getUrl()));
            }

            return gson.toJson(aluAtividadeDTO);
        }else{
            return gson.toJson(new ArrayList<AluAtividadeDTO>());
        }
    }

    @PostMapping(path="/saveAtividade",  consumes = "application/json", produces = "application/json")
    public String createAtividade(@RequestBody AtividadeDTO atividadeDTO){

        List<AluDis> l = aluDisRepository.getAluDisByIdDisciplina(atividadeDTO.getId_diciplina());

        Atividade atividade = atividadeRepository.save(AtividadeMapper.DTOtoEntity(atividadeDTO));

        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(atividadeDTO.getId_diciplina());

        for(AluDis a: l){
            Aluno aluno = new Aluno();
            aluno.setId(alunoRepository.getOne(a.getAlunofk().getId()).getId());
            AluAtividade alua = new AluAtividade(disciplina,aluno,atividade,-1,false,"");
            aluAtividadeRepository.save(alua);
        }

        return gson.toJson(atividade);

    }

    @PostMapping(path="/setFaltas",  consumes = "application/json", produces = "application/json")
    public String setFaltas(@RequestBody ArrayList<Long> ids){

        aluDisRepository.setFaltas(ids);

        return gson.toJson(true);
    }

    @PostMapping(path="/saveArquivo",  consumes = "application/json", produces = "application/json")
    public String createArquivo(@RequestBody ArquivoDTO arquivoDTO){
        return gson.toJson(arquivoRepository.save(ArquivoMapper.DTOtoEntity(arquivoDTO)));
    }

    @GetMapping("/getAtividades/{id}")
    public @ResponseBody String getAtividadesByIdDisciplina(@PathVariable Long id) {
        return gson.toJson(AtividadeMapper.ListEntitytoListDTO(atividadeRepository.getAtividadeByIdDisciplina(id)));
    }

    @GetMapping("/getArquivos/{id}")
    public @ResponseBody String getArquivosByIdDisciplina(@PathVariable Long id) {
        return gson.toJson(ArquivoMapper.ListEntitytoListDTO(arquivoRepository.getArquivosByIdDisciplina(id)));
    }

    @GetMapping("/getAlunosByDisciplina/{id}")
    public @ResponseBody String getAlunosByDisciplina(@PathVariable Long id) {
        List<AluDis> l = aluDisRepository.getAluDisByIdDisciplina(id);
        List<Long> ll= new ArrayList<>();
        for(AluDis a: l){
            ll.add(a.getAlunofk().getId());
        }

        List<Aluno> la = alunoRepository.getAlunoByListId(ll);
        return gson.toJson(AlunoMapper.ListEntitytoListDTO(la));

    }

    @GetMapping("getNotasAtividade/{ida}")
    public @ResponseBody String getNotasAtividade(@PathVariable Long ida){
        List<AluAtividade> aluAtividades = aluAtividadeRepository.getAluAtividadeByIdAtividade(ida);
        List<NotasDTO> notasDTO = new ArrayList<>();

        for(AluAtividade aluAtividade: aluAtividades){
            NotasDTO notasDTO1 = new NotasDTO();
            notasDTO1.setIdAluno(aluAtividade.getAlunofk().getId());
            notasDTO1.setNomeAluno(aluAtividade.getAlunofk().getNome());
            notasDTO1.setIdAtividade(ida);
            if(aluAtividade.getNota() < 0){
                notasDTO1.setNota((float) 0);
            }else{
                notasDTO1.setNota(aluAtividade.getNota());
            }

            notasDTO.add(notasDTO1);
        }

        return gson.toJson(notasDTO);
    }

    @PostMapping(path="/setNotasAtividade",  consumes = "application/json", produces = "application/json")
    public @ResponseBody String setNotasAtividade(@RequestBody NotasDTO[] notas){

        for(NotasDTO nota: notas){

            aluAtividadeRepository.setNotaByIdAluno(nota.getIdAluno(),nota.getIdAtividade(),nota.getNota());
        }

        return gson.toJson(true);
    }

    @DeleteMapping("/deleteArquivo/{id}")
    public String deleteArquivo(@PathVariable Long id) {
        arquivoRepository.deleteById(id);
        return gson.toJson(id);
    }

    @DeleteMapping("/deleteAtividade/{id}")
    public String deleteAtividade(@PathVariable Long id) {
        aluAtividadeRepository.deleteAtividadeByIdAtividade(id);
        atividadeRepository.deleteById(id);

        return gson.toJson(id);
    }

    @PutMapping(path="/updateAtividade",  consumes = "application/json", produces = "application/json")
    public String updateAtividade(@RequestBody AtividadeDTO a){
        atividadeRepository.updateAtividade(a.getDt_data(),a.getNu_valor_atividade(),a.getId_atividade());
        return gson.toJson(a.getId_atividade());
    }
}
