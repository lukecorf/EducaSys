package com.tcc.aluno.Repositories;

import com.tcc.aluno.database.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Login l SET l.password=:password WHERE l.code =:code")
    void updateLogin(@Param("password")String password, @Param("code") String code);
}
