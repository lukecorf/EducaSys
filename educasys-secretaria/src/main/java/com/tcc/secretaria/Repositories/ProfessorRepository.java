package com.tcc.secretaria.Repositories;

import com.tcc.secretaria.database.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long>{
    @Query("SELECT p FROM Professor p WHERE UPPER(p.nome) LIKE CONCAT('%',UPPER(:nome),'%')")
    List<Professor> searchProfessor(@Param("nome") String nome);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Professor p SET p.cpf = :cpf, p.dataN=:dataN, p.email = :email, p.endereco = :endereco, p.nome = :nome, p.rg = :rg, p.telefone = :telefone WHERE p.id = :id")
    void updateProfessor(@Param("cpf")String cpf, @Param("dataN") Date dataN, @Param("email") String email, @Param("endereco")String endereco, @Param("nome") String nome, @Param("rg") String rg, @Param("telefone")String telefone, @Param("id") Long id);

}
