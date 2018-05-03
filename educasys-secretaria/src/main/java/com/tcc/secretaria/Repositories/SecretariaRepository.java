package com.tcc.secretaria.Repositories;

import com.tcc.secretaria.database.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretariaRepository extends JpaRepository<Secretaria,Long> {
    @Query("SELECT s FROM Secretaria s WHERE s.code = :code")
    Secretaria getSecretariaByCode(@Param("code")String code);
}
