package com.tcc.secretaria.Repositories;


import com.tcc.secretaria.database.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo,Long>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Arquivo a WHERE a.disciplinafk.codigo = :codigo")
    void deleteArquivosByIdDisciplina(@Param("codigo") Long codigo);
}
