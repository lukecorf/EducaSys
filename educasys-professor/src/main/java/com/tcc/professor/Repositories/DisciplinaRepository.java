package com.tcc.professor.Repositories;

import com.tcc.professor.database.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {
    @Query("SELECT d FROM Disciplina d WHERE d.professorfk.id = :id")
    List<Disciplina> getDisciplinaByIdProfesor(@Param("id")Long id);
}
