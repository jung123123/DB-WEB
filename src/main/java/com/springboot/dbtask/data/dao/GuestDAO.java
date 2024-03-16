package com.springboot.dbtask.data.dao;

import com.springboot.dbtask.data.entity.Guest;

import java.util.List;

public interface GuestDAO {
    // Service와 DAO를 인터페이스로 만드는 것은 유연성, 확장성, 유지보수성, 테스트 용이성 등을 향상시키기 위함

    List<Guest> selectAllGuest();       //Guest 테이블 리스트 보기 select

    Guest insertGuest(Guest guest); //Guest 테이블에 값 추가 insert

    Guest updateGuest(Long id, String name) throws Exception;   //Guest 테이블 수정 update


    void deleteGuest(Long id) throws Exception;   //Guest 테이블 삭제 delete

}
