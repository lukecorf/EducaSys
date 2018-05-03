package com.tcc.secretaria.Repositories;

import com.tcc.secretaria.database.AluDis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AluDisRepository  extends JpaRepository<AluDis,Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM AluDis a WHERE a.disciplinafk.codigo = :idDis")
    void deleteByIdDisciplina(@Param("idDis")long idDis);

    @Transactional
    @Modifying
    @Query("DELETE FROM AluDis a WHERE a.alunofk.id = :idAlu")
    void deleteByIdAluno(@Param("idAlu")long idAlu);
}
