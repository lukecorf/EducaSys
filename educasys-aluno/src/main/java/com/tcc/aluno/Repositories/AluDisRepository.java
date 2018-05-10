package com.tcc.aluno.Repositories;

import com.tcc.aluno.database.AluDis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AluDisRepository  extends JpaRepository<AluDis,Long> {
    @Transactional
    @Modifying
    @Query("SELECT a FROM AluDis a WHERE a.alunofk.id = :idAlu")
    List<AluDis> getByIdAluno(@Param("idAlu") long idAlu);

    @Query("SELECT a.faltas FROM AluDis a WHERE a.alunofk.id = :idAlu AND a.disciplinafk.codigo = :idDis")
    Float getFaltas(@Param("idAlu") Long idAlu, @Param("idDis") Long idDis);
}
