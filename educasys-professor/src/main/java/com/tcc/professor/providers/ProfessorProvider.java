package com.tcc.professor.providers;

import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tcc.professor.DTO.AluAtividadeDTO;
import com.tcc.professor.DTO.ArquivoDTO;
import com.tcc.professor.DTO.AtividadeDTO;
import com.tcc.professor.DTO.DisciplinaPDTO;
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
    Gson gson = new Gson();

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    ArquivoRepository arquivoRepository;

    @Autowired
    AluDisRepository aluDisRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    AluAtividadeRepository aluAtividadeRepository;

//    @GetMapping(value = "/getAluno")
//    public @ResponseBody
//    String getAluno() {
//        System.out.println("PEGANDO ALUNO");
//        //Aluno aluno = new Aluno("0001","Lucas Alves de Faria","2018/1","10/12/1995",true,"luke@email.com","(37) 999597899","127.831.956-58","MG-19.319.265");
//
//        return gson.toJson(aluno);
//    }

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

    @PostMapping(path="/updateAtividade",  consumes = "application/json", produces = "application/json")
    public String updateAtividade(@RequestBody AtividadeDTO a){
        atividadeRepository.updateAtividade(AtividadeMapper.DTOtoEntity(a),a.getId_atividade());
        return gson.toJson(a.getId_atividade());
    }
}
