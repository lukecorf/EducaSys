package com.tcc.aluno.mapper;

import com.tcc.aluno.DTO.ArquivoDTO;
import com.tcc.aluno.database.Arquivo;
import com.tcc.aluno.database.Disciplina;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukew on 03/05/2018.
 */
public class ArquivoMapper {

    public static Arquivo DTOtoEntity(ArquivoDTO a){
        Arquivo arq = new Arquivo();
        arq.setId(a.getId_arquivo());
        arq.setNome(a.getSt_nome_arquivo());
        arq.setLink(a.getUrl_arquivo());
        arq.setTamanho(a.getNu_tamanho_arquivo());
        Disciplina d = new Disciplina();
        d.setCodigo(a.getId_disciplina());
        arq.setDisciplinafk(d);

        return arq;
    }

    public static ArquivoDTO EntitytoDTO(Arquivo a){
        ArquivoDTO arq = new ArquivoDTO();
        arq.setId_arquivo(a.getId());
        arq.setSt_nome_arquivo(a.getNome());
        arq.setUrl_arquivo(a.getLink());
        arq.setNu_tamanho_arquivo(a.getTamanho());
        arq.setId_disciplina(a.getDisciplinafk().getCodigo());

        return arq;
    }

    public static List<ArquivoDTO> ListEntitytoListDTO(List<Arquivo>la){
        List<ArquivoDTO> adto = new ArrayList<>();

        for(Arquivo a: la){
            adto.add(EntitytoDTO(a));
        }
        return adto;
    }

}
