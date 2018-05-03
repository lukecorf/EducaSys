package com.tcc.professor.Repositories;

import com.tcc.professor.database.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long>{
    @Query("SELECT p FROM Professor p WHERE p.cpf = :cpf")
    Professor getProfessorByCode(@Param("cpf")String cpf);
}
