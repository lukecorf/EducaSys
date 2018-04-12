package com.tcc.aluno.providers;

import com.google.gson.Gson;
import com.tcc.aluno.database.Disciplina;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AlunoProvider {
    Gson gson = new Gson();

    @GetMapping(value = "/getDisciplinas")
    public @ResponseBody
    String getDisciplinas(){
        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

        return gson.toJson(disciplinas);
    }
}
