package com.springboot.dbtask.data.repository;

import com.springboot.dbtask.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}
