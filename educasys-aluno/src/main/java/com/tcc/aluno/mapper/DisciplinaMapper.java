package com.tcc.aluno.mapper;

import com.tcc.aluno.DTO.DisciplinaADTO;
import com.tcc.aluno.DTO.DisciplinaDTO;
import com.tcc.aluno.DTO.DisciplinaListDTO;
import com.tcc.aluno.database.AluDis;
import com.tcc.aluno.database.Disciplina;
import com.tcc.aluno.database.Professor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public static List<DisciplinaADTO> ListEntitytoListADTO(List<Disciplina> list, List<AluDis> alu, List<Date> datas, List<Double>notas,Long id){

        List<DisciplinaADTO> l2 = new ArrayList<>();
        System.out.println("LISTA: "+list.size());
        for(int i = 0; i < list.size(); i++){
            DisciplinaADTO disciplinaADTO = new DisciplinaADTO();
            if(datas.get(i) == null){
                disciplinaADTO.setDt_next_prova("--/--");
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                disciplinaADTO.setDt_next_prova(sdf.format(datas.get(i)));
            }
            disciplinaADTO.setId_aluno(id);
            disciplinaADTO.setId_disciplina(list.get(i).getCodigo());
            disciplinaADTO.setId_professor(list.get(i).getProfessorfk().getId());
            disciplinaADTO.setNu_faltas((int) alu.get(i).getFaltas());
            disciplinaADTO.setNu_nota(notas.get(i));
            disciplinaADTO.setSt_nome(list.get(i).getNome());
            disciplinaADTO.setSt_nome_prof(list.get(i).getProfessor());
            disciplinaADTO.setUrl_img(list.get(i).getImg());
            disciplinaADTO.setNu_carga_horaria(list.get(i).getCargaH());
            l2.add(disciplinaADTO);
        }

        return  l2;
    }
}
