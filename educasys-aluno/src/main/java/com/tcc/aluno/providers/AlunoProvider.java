package com.tcc.aluno.providers;

import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tcc.aluno.Repositories.*;
import com.tcc.aluno.database.AluDis;
import com.tcc.aluno.database.Atividade;
import com.tcc.aluno.database.Disciplina;
import com.tcc.aluno.mapper.AlunoMapper;
import com.tcc.aluno.mapper.ArquivoMapper;
import com.tcc.aluno.mapper.AtividadeMapper;
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

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    ArquivoRepository arquivoRepository;

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

    @GetMapping("/getAtividades/{id}")
    public @ResponseBody String getAtividadesByIdDisciplina(@PathVariable Long id) {
        System.out.println("ID: "+id);
        List<Atividade> a = atividadeRepository.getAtividadeByIdDisciplina(id);
        System.out.println("Tamanho da Lista: "+a.size());
        return gson.toJson(AtividadeMapper.ListEntitytoListDTO(a));
    }

    @GetMapping("/getArquivos/{id}")
    public @ResponseBody String getArquivosByIdDisciplina(@PathVariable Long id) {
        return gson.toJson(ArquivoMapper.ListEntitytoListDTO(arquivoRepository.getArquivosByIdDisciplina(id)));
    }

    @GetMapping("/getFaltas/{id}/{ida}")
    public @ResponseBody String getFaltas(@PathVariable Long id, @PathVariable Long ida){
        System.out.println("ID DIS: "+id+" ID ALU: "+ida);
        return gson.toJson(aluDisRepository.getFaltas(id,ida));
    }
}
