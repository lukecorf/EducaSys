package com.tcc.aluno.Repositories;

import com.tcc.aluno.database.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/*======================================================================================================================
||Interface responsavel por implementar as Query's nativas para obtenção, atualização e remoção de dados. Referente   ||
||ao modulo de alunos. e a entidade Atividade.                                                                        ||
======================================================================================================================*/
@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
    @Query("SELECT a FROM Atividade a WHERE a.disciplinafk.codigo = :codigo")
    List<Atividade> getAtividadeByIdDisciplina(@Param("codigo") Long codigo);

    @Query("SELECT a.data FROM Atividade a WHERE a.data > :data AND a.disciplinafk.codigo = :id AND a.tipo = false")
    List<Date> getDataNextProva(@Param("data") Date data, @Param("id") Long id);

}
