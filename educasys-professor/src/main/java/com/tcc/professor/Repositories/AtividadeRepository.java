package com.tcc.professor.Repositories;

import com.tcc.professor.database.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
    @Query("SELECT a FROM Atividade a WHERE a.disciplinafk.codigo = :codigo")
    List<Atividade> getAtividadeByIdDisciplina(@Param("codigo")Long codigo);
}
