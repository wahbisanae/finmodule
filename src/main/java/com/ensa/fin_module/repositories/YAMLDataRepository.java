package com.ensa.fin_module.repositories;

import com.ensa.fin_module.models.YAMLData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface YAMLDataRepository extends JpaRepository<YAMLData, Long> {

        List<YAMLData> findByFramework(String framework);

        List<YAMLData> findByClassName(String className);
    }

