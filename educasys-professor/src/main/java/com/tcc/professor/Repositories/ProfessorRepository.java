package com.tcc.professor.Repositories;

import com.tcc.professor.database.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long>{
    @Query("SELECT p FROM Professor p WHERE p.cpf = :cpf")
    Professor getProfessorByCode(@Param("cpf")String cpf);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Professor p SET p.telefone = :telefone, p.endereco= :endereco, p.email= :email WHERE p.id = :id")
    void updateProfessorNoAll(@Param("telefone")String telefone, @Param("endereco") String endereco, @Param("email") String email, @Param("id") Long id);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Professor p SET p.telefone = :telefone, p.endereco= :endereco, p.email= :email, p.url = :url WHERE p.id = :id")
    void updateProfessor(@Param("telefone")String telefone, @Param("endereco") String endereco, @Param("email") String email,@Param("url")String url, @Param("id") Long id);

}
