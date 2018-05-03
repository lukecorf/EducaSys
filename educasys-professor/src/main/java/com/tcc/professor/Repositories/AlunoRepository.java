package com.tcc.professor.Repositories;

import com.tcc.professor.database.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    @Query("SELECT a FROM Aluno a WHERE a.id IN :list")
    List<Aluno> getAlunoByListId(@Param("list")List<Long> list);
}
