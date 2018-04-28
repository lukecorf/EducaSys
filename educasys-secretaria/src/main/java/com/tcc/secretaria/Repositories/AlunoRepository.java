package com.tcc.secretaria.Repositories;

import com.tcc.secretaria.database.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository  extends JpaRepository<Aluno,Long> {
}
