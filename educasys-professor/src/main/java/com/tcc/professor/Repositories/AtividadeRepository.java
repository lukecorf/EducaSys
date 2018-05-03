package com.tcc.professor.Repositories;

import com.tcc.professor.database.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
}
