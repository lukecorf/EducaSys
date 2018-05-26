package com.tcc.secretaria.Repositories;


import com.tcc.secretaria.database.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
    @Query("SELECT a FROM Atividade a WHERE a.disciplinafk.codigo = :codigo")
    List<Atividade> getAtividadeByIdDisciplina(@Param("codigo") Long codigo);

    @Transactional
    @Modifying
    @Query("DELETE FROM Atividade a WHERE a.disciplinafk.codigo = :codigo")
    void deleteAtividadeByIdDisciplina(@Param("codigo")Long codigo);
}
