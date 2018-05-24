package com.tcc.secretaria.Repositories;

import com.tcc.secretaria.database.Disciplina;
import com.tcc.secretaria.database.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {
    @Query("SELECT d FROM Disciplina d WHERE UPPER(d.nome) LIKE CONCAT('%',UPPER(:nome),'%')")
    List<Disciplina> searchDisciplina(@Param("nome") String nome);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Disciplina d SET d.nome= :nome,  d.cargaH = :cargaH, d.descricao = :descricao, d.img = :img WHERE d.codigo = :codigo")
    void updateDisciplina(@Param("nome")String nome, @Param("cargaH") int cargaH, @Param("descricao") String descricao, @Param("img")String img, @Param("codigo")Long codigo);
}
