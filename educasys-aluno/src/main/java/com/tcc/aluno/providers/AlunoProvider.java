package com.tcc.aluno.providers;

import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tcc.aluno.DTO.DisciplinaADTO;
import com.tcc.aluno.DTO.EntregaDTO;
import com.tcc.aluno.Repositories.*;
import com.tcc.aluno.database.AluAtividade;
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
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AlunoProvider {
    Gson gson = new Gson();

    @Autowired
    AluAtividadeRepository aluAtividadeRepository;

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
        Date hoje = new Date( System.currentTimeMillis());
        List<Date> datas = new ArrayList<>();
        List<Double> notas = new ArrayList<>();
        for(Disciplina dis: disciplinas){
            List<Date> datasProvas = atividadeRepository.getDataNextProva(hoje,dis.getCodigo());
            Double notaDis = aluAtividadeRepository.getSumNotasByAluDis(dis.getCodigo(),id);
            if( notaDis == null ||notaDis < 0){
                notas.add((double) 0);
            }else{
                notas.add(notaDis);
            }
            if(datasProvas.size() > 0){
                datas.add(datasProvas.get(0));
            }else{
                datas.add(null);
            }
        }

        List<DisciplinaADTO> disciplinaADTOS = DisciplinaMapper.ListEntitytoListADTO(disciplinas,aluDis,datas,notas,id);
        return gson.toJson(disciplinaADTOS);
    }

    @GetMapping(value ="/getAlunoByCode/{id}")
    public @ResponseBody String getAlunoByCode(@PathVariable String id){
        return gson.toJson(AlunoMapper.EntitytoDTO(alunoRepository.getAlunoByCode(id)));
    }

    @GetMapping("/getDisciplinaById/{id}")
    public @ResponseBody String getDisciplinaById(@PathVariable Long id) {
        return gson.toJson(DisciplinaMapper.EntitytoDTO(disciplinaRepository.getOne(id)));
    }

    @GetMapping("/getAtividades/{id}/{ida}")
    public @ResponseBody String getAtividadesByIdDisciplina(@PathVariable Long id,@PathVariable Long ida) {
        List<Atividade> a = atividadeRepository.getAtividadeByIdDisciplina(id);
        List<AluAtividade> aluAtividades = aluAtividadeRepository.getAtividadeByDisAlu(ida,id);
        return gson.toJson(AtividadeMapper.ListEntitytoListDTO(a,aluAtividades));
    }


    @PostMapping(path="/setAtividade",  consumes = "application/json", produces = "application/json")
    public String entregaAtividade(@RequestBody EntregaDTO entregaDTO){
        aluAtividadeRepository.atualizaAtividade(entregaDTO.getUrl(),entregaDTO.getId_aluno(),entregaDTO.getId_atividade());

        return gson.toJson(entregaDTO);

    }

    @GetMapping("/getArquivos/{id}")
    public @ResponseBody String getArquivosByIdDisciplina(@PathVariable Long id) {
        return gson.toJson(ArquivoMapper.ListEntitytoListDTO(arquivoRepository.getArquivosByIdDisciplina(id)));
    }

    @GetMapping("/getFaltas/{id}/{ida}")
    public @ResponseBody String getFaltas(@PathVariable Long id, @PathVariable Long ida){
        return gson.toJson(aluDisRepository.getFaltas(id,ida));
    }
}
