package com.tcc.aluno.Repositories;

import com.tcc.aluno.database.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

/*======================================================================================================================
||Interface responsavel por implementar as Query's nativas para obtenção, atualização e remoção de dados. Referente   ||
||ao modulo de alunos. e a entidade Aluno.                                                                            ||
======================================================================================================================*/
@Repository
public interface AlunoRepository  extends JpaRepository<Aluno,Long> {
    @Query("SELECT a FROM Aluno a WHERE a.cpf = :cpf")
    Aluno getAlunoByCode(@Param("cpf") String cpf);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Aluno a SET a.telefone = :telefone, a.endereco= :endereco, a.email= :email WHERE a.id = :id")
    void updateAlunoNoAll(@Param("telefone")String telefone, @Param("endereco") String endereco, @Param("email") String email, @Param("id") Long id);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Aluno a SET a.telefone = :telefone, a.endereco= :endereco, a.email= :email, a.url = :url WHERE a.id = :id")
    void updateAluno(@Param("telefone")String telefone, @Param("endereco") String endereco, @Param("email") String email,@Param("url")String url, @Param("id") Long id);


}
