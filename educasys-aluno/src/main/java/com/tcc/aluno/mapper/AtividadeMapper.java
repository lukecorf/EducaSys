package com.tcc.aluno.mapper;

import com.tcc.aluno.DTO.AtividadeDTO;
import com.tcc.aluno.database.Atividade;
import com.tcc.aluno.database.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class AtividadeMapper {

    public static Atividade DTOtoEntity(AtividadeDTO a){
        Atividade ati = new Atividade();
        ati.setData(a.getDt_data());
        Disciplina d = new Disciplina();
        ati.setDisciplinafk(d);
        d.setCodigo(a.getId_diciplina());
        ati.setId(a.getId_atividade());
        ati.setNome(a.getSt_nome_atividade());
        ati.setTipo(a.isBo_tipo_atividade());
        ati.setValor(a.getNu_valor_atividade());
        return ati;
    }

    public static AtividadeDTO EntitytoDTO(Atividade a){
        AtividadeDTO ati = new AtividadeDTO();
        ati.setDt_data(a.getData());
        ati.setId_atividade(a.getId());
        ati.setId_diciplina(a.getDisciplinafk().getCodigo());
        ati.setBo_tipo_atividade(a.isTipo());
        ati.setSt_nome_atividade(a.getNome());
        ati.setNu_valor_atividade(a.getValor());
        return ati;
    }

    public static List<AtividadeDTO>ListEntitytoListDTO(List<Atividade> la){
        List<AtividadeDTO> ldto = new ArrayList<>();

        for(Atividade a: la){
            ldto.add(EntitytoDTO(a));
        }
        return ldto;
    }
}
