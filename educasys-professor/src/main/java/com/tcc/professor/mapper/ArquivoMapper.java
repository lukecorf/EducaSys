package com.tcc.professor.mapper;

import com.tcc.professor.DTO.ArquivoDTO;
import com.tcc.professor.database.Arquivo;
import com.tcc.professor.database.Disciplina;

/**
 * Created by lukew on 03/05/2018.
 */
public class ArquivoMapper {

    public static Arquivo DTOtoEntity(ArquivoDTO a){
        Arquivo arq = new Arquivo();
        arq.setId(a.getId_arquivo());
        arq.setLink(a.getUrl_arquivo());
        arq.setNome(a.getSt_nome_arquivo());
        arq.setTamanho(a.getNu_tamanho_arquivo());
        Disciplina d = new Disciplina();
        d.setCodigo(a.getId_disciplina());
        arq.setDisciplinafk(d);

        return arq;
    }

}
