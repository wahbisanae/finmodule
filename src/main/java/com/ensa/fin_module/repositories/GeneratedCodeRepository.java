package com.ensa.fin_module.repositories;


import com.ensa.fin_module.models.GeneratedCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneratedCodeRepository extends JpaRepository<GeneratedCode, Long> {
    List<GeneratedCode> findByFramework(String framework);

}

