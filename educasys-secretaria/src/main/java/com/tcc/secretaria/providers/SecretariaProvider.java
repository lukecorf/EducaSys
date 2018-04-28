package com.tcc.secretaria.providers;

import com.google.gson.Gson;
import com.tcc.secretaria.DTO.*;
import com.tcc.secretaria.Repositories.AlunoRepository;
import com.tcc.secretaria.Repositories.ProfessorRepository;
import com.tcc.secretaria.database.Aluno;
import com.tcc.secretaria.database.Professor;
import com.tcc.secretaria.mapper.AlunoMapper;
import com.tcc.secretaria.mapper.ProfessorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SecretariaProvider {
    Gson gson = new Gson();

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping(value = "/getDisciplinas")
    public @ResponseBody String getDisciplinas(){
        ArrayList<DisciplinaListDTO> disciplinas = new ArrayList<DisciplinaListDTO>();

        disciplinas.add(new DisciplinaListDTO(1L,"Web Avançado","Bruno Ferreira",60,"23/12"));
        disciplinas.add(new DisciplinaListDTO(2L,"Redes","Everthon Valadão",60,"11/11"));
        disciplinas.add(new DisciplinaListDTO(3L,"Banco de Dados","Patricia Proença",30,"10/12"));
        disciplinas.add(new DisciplinaListDTO(4L,"Mobile","Diego Mello",30,"13/10"));

        return gson.toJson(disciplinas);
    }





    @GetMapping("/getDisciplinaById/{id}")
    public @ResponseBody String getDisciplinaById(@PathVariable Long id) {
        System.out.println("ID: "+id);

        ArrayList<AlunoListDTO> list = new ArrayList<AlunoListDTO>();

        list.add(new AlunoListDTO(1L,"Lucas Alves","10/12/1995","3799998888"));
        list.add(new AlunoListDTO(2L,"Rafaela Bitencourt","10/12/1995","3799998888"));
        list.add(new AlunoListDTO(3L,"João Paulo","10/12/1995","3799998888"));
        list.add(new AlunoListDTO(4L,"Rodrigo Silva","10/12/1995","3799998888"));

        DisciplinaDTO disciplina = new DisciplinaDTO(1l,"Web Avançado",60,"Abrange as mais novas tecnologias de desenvolvimento web","Bruno Ferreira",list);

        return gson.toJson(disciplina);

    }

    @GetMapping(value = "/getAlunos")
    public @ResponseBody String getAlunos(){
        System.out.println("Entrou no get professor");
        List<Aluno> list;
        list = alunoRepository.findAll();
        return gson.toJson(AlunoMapper.ListEntitytoListDTO(list));

    }

    @GetMapping("/getAlunoById/{id}")
    public @ResponseBody String getAlunoById(@PathVariable Long id) {
        Aluno a = alunoRepository.getOne(id);
        return gson.toJson(AlunoMapper.EntitytoDTO(a));

    }

    @PostMapping(path="/saveAluno",  consumes = "application/json", produces = "application/json")
    public String createAluno(@RequestBody AlunoDTO alunoDTO){
        System.out.println("Entrou no save Aluno");
        System.out.println(alunoDTO.toString());
        Aluno a = AlunoMapper.DTOtoEntity(alunoDTO);
        return gson.toJson(alunoRepository.save(a));
    }

    @DeleteMapping("/deleteAluno/{id}")
    public String deleteAluno(@PathVariable Long id) {
        System.out.println("Delete");
        alunoRepository.deleteById(id);

        return gson.toJson(id);
    }

    @GetMapping(value = "/getProfessores")
    public @ResponseBody String getProfessores(){
        System.out.println("Entrou no get professor");
        List<Professor> list;
        list = professorRepository.findAll();
        return gson.toJson(ProfessorMapper.ListEntitytoListDTO(list));
    }

    @GetMapping("/getProfessorById/{id}")
    public @ResponseBody String getProfessorById(@PathVariable Long id) {

        Professor p = professorRepository.getOne(id);
        return gson.toJson(ProfessorMapper.EntitytoDTO(p));

    }

    @PostMapping(path="/saveProfessor",  consumes = "application/json", produces = "application/json")
    public String createProfessor(@RequestBody ProfessorDTO professorDTO){
            System.out.println("Entrou");
            Professor p = ProfessorMapper.DTOtoEntity(professorDTO);
            return gson.toJson(professorRepository.save(p));
    }

    @DeleteMapping("/deleteProfessor/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        System.out.println("Delete");
        professorRepository.deleteById(id);

        return gson.toJson(id);
    }
}
