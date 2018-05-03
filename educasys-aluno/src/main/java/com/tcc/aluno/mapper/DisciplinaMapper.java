package com.tcc.aluno.mapper;

import com.tcc.aluno.DTO.DisciplinaADTO;
import com.tcc.aluno.DTO.DisciplinaDTO;
import com.tcc.aluno.DTO.DisciplinaListDTO;
import com.tcc.aluno.database.AluDis;
import com.tcc.aluno.database.Disciplina;
import com.tcc.aluno.database.Professor;

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
        dis.setProfessorfk(p);
        p.setId(d.getId_professor());
        return dis;
    }


    public static DisciplinaDTO EntitytoDTO(Disciplina dis){
        DisciplinaDTO d = new DisciplinaDTO();

        d.setId_disciplina(dis.getCodigo());
        d.setId_professor(dis.getProfessorfk().getId());
        d.setNu_carga_horaria(dis.getCargaH());
        d.setSt_nome(dis.getNome());
        d.setSt_nome_prof(dis.getProfessor());
        d.setUrl_img(dis.getImg());
        d.setTx_descricao(dis.getDescricao());

        return d;
    }

    public static List<DisciplinaListDTO> ListEntitytoListDTO(List<Disciplina> list){

        List<DisciplinaListDTO> l2 = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            l2.add(new DisciplinaListDTO(list.get(i).getCodigo(),list.get(i).getNome(),list.get(i).getProfessor(),list.get(i).getCargaH(),"2018/1"));
        }

        return  l2;
    }

    public static List<DisciplinaADTO> ListEntitytoListADTO(List<Disciplina> list,List<AluDis> alu){

        List<DisciplinaADTO> l2 = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            l2.add(new DisciplinaADTO(list.get(i).getCodigo(),list.get(i).getNome(),list.get(i).getCargaH(),list.get(i).getDescricao(),list.get(i).getProfessor(),list.get(i).getImg(),list.get(i).getProfessorfk().getId(),(int)alu.get(i).getFaltas()));
        }

        return  l2;
    }
}
