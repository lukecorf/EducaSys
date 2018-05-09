package com.tcc.secretaria.Repositories;

import com.tcc.secretaria.database.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {
    @Query("SELECT d FROM Disciplina d WHERE UPPER(d.nome) LIKE CONCAT('%',UPPER(:nome),'%')")
    List<Disciplina> searchDisciplina(@Param("nome") String nome);
}
