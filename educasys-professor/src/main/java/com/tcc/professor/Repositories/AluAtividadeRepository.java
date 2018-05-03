package com.tcc.professor.Repositories;

import com.tcc.professor.database.AluAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluAtividadeRepository extends JpaRepository<AluAtividade, Long>{
}
