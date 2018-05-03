package com.tcc.professor.Repositories;

import com.tcc.professor.database.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo,Long>{
}
