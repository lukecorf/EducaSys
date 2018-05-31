package com.tcc.professor.Repositories;

import com.tcc.professor.database.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/*======================================================================================================================
||Interface responsavel por implementar as Query's nativas para obtenção, atualização e remoção de dados. Referente   ||
||ao modulo de Professores. e a entidade Arquivo.                                                                    ||
======================================================================================================================*/
@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo,Long>{
    @Query("SELECT a FROM Arquivo a WHERE a.disciplinafk.codigo = :codigo")
    List<Arquivo>getArquivosByIdDisciplina(@Param("codigo") Long codigo);
}
