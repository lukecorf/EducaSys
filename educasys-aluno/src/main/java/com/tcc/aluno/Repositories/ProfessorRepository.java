package com.tcc.aluno.Repositories;

import com.tcc.aluno.database.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*======================================================================================================================
||Interface responsavel por implementar as Query's nativas para obtenção, atualização e remoção de dados. Referente   ||
||ao modulo de alunos. e a entidade Professor.                                                                        ||
======================================================================================================================*/
@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long>{
}
