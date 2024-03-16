package com.springboot.dbtask.data.repository;

import com.springboot.dbtask.data.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature,Long> {
}
