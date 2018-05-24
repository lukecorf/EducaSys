package com.tcc.secretaria.mapper;


import com.tcc.secretaria.DTO.AlunoDTO;
import com.tcc.secretaria.DTO.AlunoListDTO;
import com.tcc.secretaria.database.Aluno;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AlunoMapper {
    public static Aluno DTOtoEntity(AlunoDTO a){
        Aluno alu = new Aluno();
        alu.setCpf(a.getDc_cpf());
        alu.setRg(a.getDc_rg());
        StringTokenizer st = new StringTokenizer(a.getDt_data_nasc(),"-");
        int year = Integer.parseInt(st.nextToken());
        int mouth = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        LocalDate localDate = LocalDate.of(year,mouth,day);
        alu.setDataN(localDate);
        alu.setEmail(a.getCo_email());
        alu.setTelefone(a.getCo_telefone());
        alu.setEndereco(a.getSt_endereco());
        alu.setId(0);
        alu.setNome(a.getSt_nome_aluno());
        alu.setUrl(a.getUrl_img_aluno());
        alu.setNomeP(a.getSt_nome_pai());
        alu.setNomeM(a.getSt_nome_mae());
        return alu;
    }

    public static AlunoDTO EntitytoDTO(Aluno a){
        AlunoDTO alu = new AlunoDTO();
        alu.setDc_cpf(a.getCpf());
        if(a.getDataN().getDayOfMonth()<= 9) {
            alu.setDt_data_nasc(a.getDataN().getYear() + "-" + a.getDataN().getMonth().getValue() + "-0" + a.getDataN().getDayOfMonth());
        }else {
            alu.setDt_data_nasc(a.getDataN().getYear() + "-" + a.getDataN().getMonth().getValue() + "-" + a.getDataN().getDayOfMonth());
        }
        alu.setCo_email(a.getEmail());
        alu.setSt_endereco(a.getEndereco());
        alu.setId_aluno(a.getId());
        alu.setSt_nome_aluno(a.getNome());
        alu.setDc_rg(a.getRg());
        alu.setCo_telefone(a.getTelefone());
        alu.setUrl_img_aluno(a.getUrl());
        alu.setSt_nome_mae(a.getNomeM());
        alu.setSt_nome_pai(a.getNomeP());
        return alu;
    }

    public static List<AlunoListDTO> ListEntitytoListDTO(List<Aluno> list){

        List<AlunoListDTO> l2 = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            l2.add(new AlunoListDTO(list.get(i).getId(),list.get(i).getNome(),list.get(i).getEmail(),list.get(i).getTelefone(),list.get(i).getCpf()));
        }

        return  l2;
    }
}
