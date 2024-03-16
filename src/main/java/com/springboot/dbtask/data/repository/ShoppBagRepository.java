package com.springboot.dbtask.data.repository;

import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Orderr;
import com.springboot.dbtask.data.entity.ShoppBag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ShoppBagRepository extends JpaRepository<ShoppBag,Long> {

    List<ShoppBag> findByGuest(Guest guest);    // 쇼핑백 테이블에서 고객 번호 찾기


}
