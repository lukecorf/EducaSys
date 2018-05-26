package com.tcc.professor.Repositories;

import com.tcc.professor.database.AluDis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AluDisRepository extends JpaRepository<AluDis,Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM AluDis a WHERE a.disciplinafk.codigo = :idDis")
    void deleteByIdDisciplina(@Param("idDis") long idDis);

    @Transactional
    @Modifying
    @Query("DELETE FROM AluDis a WHERE a.alunofk.id = :idAlu")
    void deleteByIdAluno(@Param("idAlu") long idAlu);

    @Query("SELECT a FROM AluDis a WHERE a.disciplinafk.codigo = :codigo")
    List<AluDis> getAluDisByIdDisciplina(@Param("codigo") Long codigo);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE AluDis a SET a.faltas = a.faltas+1 WHERE a.alunofk.id IN :list AND a.disciplinafk.codigo =:idd")
    void setFaltas(@Param("list")List<Long> list,@Param("idd") Long idd);
}
