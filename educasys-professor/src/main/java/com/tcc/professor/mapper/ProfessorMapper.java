package com.tcc.professor.mapper;



import com.tcc.professor.DTO.ProfessorDTO;
import com.tcc.professor.DTO.ProfessorListDTO;
import com.tcc.professor.database.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukew on 26/04/2018.
 */
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
        pro.setSenha(p.getPw_senha_prof());
        pro.setTelefone(p.getCo_telefone());
        pro.setUrl(p.getUrl_img_professor());
        return pro;

    }

    public static ProfessorDTO EntitytoDTO(Professor p){
        ProfessorDTO pro = new ProfessorDTO();
        pro.setDt_data_nasc(null);
        pro.setdc_cpf(p.getCpf());
        pro.setCo_email(p.getEmail());
        pro.setSt_endereco(p.getEndereco());
        pro.setId_professor(p.getId());
        pro.setSt_nome_professor(p.getNome());
        pro.setdc_rg(p.getRg());
        pro.setPw_senha_prof(p.getSenha());
        pro.setCo_telefone(p.getTelefone());
        pro.setUrl_img_professor(p.getUrl());
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
