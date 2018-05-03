package com.tcc.aluno.Repositories;

import com.tcc.aluno.database.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long>{
}
