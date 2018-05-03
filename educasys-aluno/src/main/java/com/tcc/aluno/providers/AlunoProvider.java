package com.tcc.aluno.providers;

import com.google.gson.Gson;
import com.tcc.aluno.Repositories.AluDisRepository;
import com.tcc.aluno.Repositories.AlunoRepository;
import com.tcc.aluno.Repositories.DisciplinaRepository;
import com.tcc.aluno.database.AluDis;
import com.tcc.aluno.database.Disciplina;
import com.tcc.aluno.mapper.AlunoMapper;
import com.tcc.aluno.mapper.DisciplinaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AlunoProvider {
    Gson gson = new Gson();

    @Autowired
    AluDisRepository aluDisRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping(value = "/getDisciplinas/{id}")
    public @ResponseBody
    String getDisciplinas(@PathVariable Long id){
        List<AluDis> aluDis = aluDisRepository.getByIdAluno(id);

        List<Long> listId = new ArrayList<Long>();
        for(AluDis alu: aluDis){
            listId.add(alu.getDisciplinafk().getCodigo());
        }
        List<Disciplina> disciplinas = disciplinaRepository.getDisciplinasByIdAluDis(listId);

        return gson.toJson(DisciplinaMapper.ListEntitytoListADTO(disciplinas,aluDis));
    }

    @GetMapping(value ="/getAlunoByCode/{id}")
    public @ResponseBody String getAlunoByCode(@PathVariable String id){
        System.out.println("ENTREI: "+ id);
        return gson.toJson(AlunoMapper.EntitytoDTO(alunoRepository.getAlunoByCode(id)));
    }

    @GetMapping("/getDisciplinaById/{id}")
    public @ResponseBody String getDisciplinaById(@PathVariable Long id) {
        return gson.toJson(DisciplinaMapper.EntitytoDTO(disciplinaRepository.getOne(id)));
    }
}
