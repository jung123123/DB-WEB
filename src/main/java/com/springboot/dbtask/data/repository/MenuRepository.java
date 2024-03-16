package com.springboot.dbtask.data.repository;

import com.springboot.dbtask.data.entity.Feature;
import com.springboot.dbtask.data.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByFeature(Feature feature);          ////Menu 테이블 리스트 에서 Feature 찾기?
}
