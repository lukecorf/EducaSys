package com.tcc.secretaria.Repositories;

import com.tcc.secretaria.database.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository  extends JpaRepository<Aluno,Long> {
    @Query("SELECT a FROM Aluno a WHERE UPPER(a.nome) LIKE CONCAT('%',UPPER(:nome),'%')")
    List<Aluno> searchAluno(@Param("nome") String nome);
}
