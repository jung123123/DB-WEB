package com.springboot.dbtask.data.repository;

import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Orderr;
import com.springboot.dbtask.data.entity.ShoppBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orderr, Long> {


    List<Orderr> findByGuest(Guest guest);  //orderr 테이블에서 고객 번호 찾기
}
