package com.tcc.professor.mapper;


import com.tcc.professor.DTO.ProfessorDTO;
import com.tcc.professor.database.Professor;

import java.text.SimpleDateFormat;

public class ProfessorMapper {
    public static Professor DTOtoEntity(ProfessorDTO p){
        Professor pro = new Professor();
        pro.setCpf(p.getdc_cpf());
        pro.setDataN(null);
        pro.setEndereco(p.getSt_endereco());
        pro.setEmail(p.getCo_email());
        pro.setId(0);
        pro.setNome(p.getSt_nome_professor());
        pro.setRg(p.getdc_rg());
        pro.setTelefone(p.getCo_telefone());
        pro.setUrl(p.getUrl_img_professor());
        return pro;

    }

    public static ProfessorDTO EntitytoDTO(Professor p){
        ProfessorDTO pro = new ProfessorDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        pro.setDt_data_nasc(sdf.format(p.getDataN()));
        pro.setdc_cpf(p.getCpf());
        pro.setCo_email(p.getEmail());
        pro.setSt_endereco(p.getEndereco());
        pro.setId_professor(p.getId());
        pro.setSt_nome_professor(p.getNome());
        pro.setdc_rg(p.getRg());
        pro.setPw_senha_prof("");
        pro.setCo_telefone(p.getTelefone());
        pro.setUrl_img_professor(p.getUrl());
        return pro;

    }
}
