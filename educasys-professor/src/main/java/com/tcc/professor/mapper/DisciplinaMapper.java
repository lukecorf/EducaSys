package com.tcc.professor.mapper;



import com.tcc.professor.DTO.DisciplinaDTO;
import com.tcc.professor.DTO.DisciplinaListDTO;
import com.tcc.professor.DTO.DisciplinaPDTO;
import com.tcc.professor.database.Disciplina;
import com.tcc.professor.database.Professor;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaMapper {
    public static Disciplina DTOtoEntity(DisciplinaDTO d){
        Disciplina dis = new Disciplina();
        dis.setCodigo(0);
        dis.setDescricao(d.getTx_descricao());
        dis.setCargaH(d.getNu_carga_horaria());
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
        d.setNu_carga_horaria(dis.getCargaH());
        d.setId_professor(dis.getProfessorfk().getId());
        d.setSt_nome(dis.getNome());
        d.setSt_nome_prof(dis.getProfessor());
        d.setTx_descricao(dis.getDescricao());
        d.setUrl_img(dis.getImg());

        return d;
    }

    public static List<DisciplinaPDTO> ListEntitytoListADTO(List<Disciplina> list){

        List<DisciplinaPDTO> l2 = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            l2.add(new DisciplinaPDTO(list.get(i).getCodigo(),list.get(i).getNome(),list.get(i).getCargaH(),list.get(i).getDescricao(),list.get(i).getProfessor(),list.get(i).getImg(),list.get(i).getProfessorfk().getId()));
        }

        return  l2;
    }
}
