package com.tcc.secretaria.mapper;

import com.tcc.secretaria.DTO.DisciplinaDTO;
import com.tcc.secretaria.DTO.DisciplinaListDTO;
import com.tcc.secretaria.database.Disciplina;
import com.tcc.secretaria.database.Professor;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaMapper {
    public static Disciplina DTOtoEntity(DisciplinaDTO d){
        Disciplina dis = new Disciplina();
        dis.setCodigo(0);
        dis.setCargaH(d.getNu_carga_horaria());
        dis.setDescricao(d.getTx_descricao());
        dis.setImg(d.getUrl_img());
        dis.setNome(d.getSt_nome());
        dis.setProfessor(d.getSt_nome_prof());
        Professor p= new Professor();
        p.setId(d.getId_professor());
        dis.setProfessorfk(p);
        return dis;
    }

    public static DisciplinaDTO EntitytoDTO(Disciplina dis){
        DisciplinaDTO d = new DisciplinaDTO();

        d.setId_disciplina(dis.getCodigo());
        d.setId_professor(dis.getProfessorfk().getId());
        d.setNu_carga_horaria(dis.getCargaH());
        d.setSt_nome(dis.getNome());
        d.setSt_nome_prof(dis.getProfessor());
        d.setTx_descricao(dis.getDescricao());
        d.setUrl_img(dis.getImg());

        return d;
    }

    public static List<DisciplinaListDTO> ListEntitytoListDTO(List<Disciplina> list){

        List<DisciplinaListDTO> l2 = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            l2.add(new DisciplinaListDTO(list.get(i).getCodigo(),list.get(i).getNome(),list.get(i).getProfessor(),list.get(i).getCargaH(),"2018/1"));
        }

        return  l2;
    }
}
