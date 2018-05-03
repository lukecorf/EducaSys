package com.tcc.professor.mapper;

import com.tcc.professor.DTO.AtividadeDTO;
import com.tcc.professor.database.Atividade;
import com.tcc.professor.database.Disciplina;
import com.tcc.professor.database.Professor;

/**
 * Created by lukew on 03/05/2018.
 */
public class AtividadeMapper {

    public static Atividade DTOtoEntity(AtividadeDTO a){
        Atividade ati = new Atividade();
        ati.setData(a.getDt_data());
        Disciplina d = new Disciplina();
        d.setCodigo(a.getId_diciplina());
        ati.setDisciplinafk(d);
        ati.setId(a.getId_atividade());
        ati.setNome(a.getSt_nome_atividade());
        ati.setTipo(a.isBo_tipo_atividade());
        ati.setValor(a.getNu_valor_atividade());
        return ati;
    }
}
