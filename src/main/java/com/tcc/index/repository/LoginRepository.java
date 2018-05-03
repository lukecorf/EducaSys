package com.tcc.index.repository;

import com.tcc.index.database.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    @Transactional
    @Query("SELECT l FROM Login l WHERE l.code = :code")
    Login verifyLogin(@Param("code") String code);
}
