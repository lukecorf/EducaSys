package com.tcc.aluno.mapper;

import com.tcc.aluno.DTO.ProfessorDTO;
import com.tcc.aluno.DTO.ProfessorListDTO;
import com.tcc.aluno.database.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorMapper {
    public static Professor DTOtoEntity(ProfessorDTO p){
        Professor pro = new Professor();
        pro.setCpf(p.getdc_cpf());
        pro.setDataN(null);
        pro.setEmail(p.getCo_email());
        pro.setEndereco(p.getSt_endereco());
        pro.setId(0);
        pro.setNome(p.getSt_nome_professor());
        pro.setRg(p.getdc_rg());
        pro.setTelefone(p.getCo_telefone());
        pro.setUrl(p.getUrl_img_professor());
        return pro;

    }

    public static ProfessorDTO EntitytoDTO(Professor p){
        ProfessorDTO pro = new ProfessorDTO();
        pro.setdc_cpf(p.getCpf());
        pro.setDt_data_nasc(null);
        pro.setCo_email(p.getEmail());
        pro.setSt_endereco(p.getEndereco());
        pro.setId_professor(p.getId());
        pro.setSt_nome_professor(p.getNome());
        pro.setdc_rg(p.getRg());
        pro.setPw_senha_prof("");
        pro.setUrl_img_professor(p.getUrl());
        pro.setCo_telefone(p.getTelefone());
        return pro;
    }

    public static List<ProfessorListDTO> ListEntitytoListDTO(List<Professor> list){

        List<ProfessorListDTO> l2 = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            l2.add(new ProfessorListDTO(list.get(i).getId(),list.get(i).getNome(),list.get(i).getEmail(),list.get(i).getTelefone()));
        }

        return  l2;
    }
}
