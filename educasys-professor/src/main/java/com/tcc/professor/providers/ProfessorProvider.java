package com.tcc.professor.providers;

import com.google.gson.Gson;
import com.tcc.professor.TADs.Aluno;
import com.tcc.professor.TADs.Disciplina;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProfessorProvider {
    Gson gson = new Gson();

    @GetMapping(value = "/getAluno")
    public @ResponseBody
    String getAluno() {
        System.out.println("PEGANDO ALUNO");
        Aluno aluno = new Aluno("0001","Lucas Alves de Faria","2018/1","10/12/1995",true,"luke@email.com","(37) 999597899","127.831.956-58","MG-19.319.265");

        return gson.toJson(aluno);
    }

    @GetMapping(value = "/getDisciplinas")
    public @ResponseBody String getDisciplinas(){
        System.out.println("PEGANDO MATERIAS");
        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

        disciplinas.add(new Disciplina("001","Web Avançado","Matriculado","Bruno Ferreira","23/12","http://s3.envato.com/files/217699422/cover.__large_preview.jpg",(float)26.0,(float)12.4));
        disciplinas.add(new Disciplina("002","Redes","Matriculado","Everthon Valadão","11/11","http://helpdigitalti.com.br/wp-content/uploads/2017/02/rede1.jpg",(float)3.0,(float)30.6));
        disciplinas.add(new Disciplina("003","Banco de Dados","Matriculado","Patricia Proença","10/12","https://d2tycqyw09ngo1.cloudfront.net/be-content/uploads/2017/03/10114900/curso-online-de-banco-de-dados-relacional-e-linguagem-sql-BECODE-new-1.png",(float)11,(float)22.8));
        disciplinas.add(new Disciplina("004","Mobile","Matriculado","Diego Mello","13/10","http://blog.newrelic.com/wp-content/uploads/shutterstock_241331182.jpg",(float)26,(float)12.4));

        return gson.toJson(disciplinas);
    }
}
