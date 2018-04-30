package com.tcc.secretaria.Repositories;

import com.tcc.secretaria.database.AluDis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluDisRepository  extends JpaRepository<AluDis,Long> {
}
