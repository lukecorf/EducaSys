package com.tcc.professor.providers;

import com.google.gson.Gson;
import com.tcc.professor.DTO.ArquivoDTO;
import com.tcc.professor.DTO.AtividadeDTO;
import com.tcc.professor.DTO.DisciplinaPDTO;
import com.tcc.professor.Repositories.ArquivoRepository;
import com.tcc.professor.Repositories.AtividadeRepository;
import com.tcc.professor.Repositories.DisciplinaRepository;
import com.tcc.professor.Repositories.ProfessorRepository;
import com.tcc.professor.TADs.Aluno;
import com.tcc.professor.database.Disciplina;
import com.tcc.professor.mapper.ArquivoMapper;
import com.tcc.professor.mapper.AtividadeMapper;
import com.tcc.professor.mapper.DisciplinaMapper;
import com.tcc.professor.mapper.ProfessorMapper;
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

    @GetMapping(value = "/getAluno")
    public @ResponseBody
    String getAluno() {
        System.out.println("PEGANDO ALUNO");
        Aluno aluno = new Aluno("0001","Lucas Alves de Faria","2018/1","10/12/1995",true,"luke@email.com","(37) 999597899","127.831.956-58","MG-19.319.265");

        return gson.toJson(aluno);
    }

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

    @PostMapping(path="/saveAtividade",  consumes = "application/json", produces = "application/json")
    public String createAtividade(@RequestBody AtividadeDTO atividadeDTO){

        return gson.toJson(atividadeRepository.save(AtividadeMapper.DTOtoEntity(atividadeDTO)));
    }

    @PostMapping(path="/saveArquivo",  consumes = "application/json", produces = "application/json")
    public String createArquivo(@RequestBody ArquivoDTO arquivoDTO){
        return gson.toJson(arquivoRepository.save(ArquivoMapper.DTOtoEntity(arquivoDTO)));
    }
}
