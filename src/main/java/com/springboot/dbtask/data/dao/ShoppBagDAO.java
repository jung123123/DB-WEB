package com.springboot.dbtask.data.dao;

import com.springboot.dbtask.data.entity.*;

import java.util.List;

public interface ShoppBagDAO {


    List<ShoppBag> selectAllShoppBag();       //ShoppBag 테이블 리스트 보기 select

    List<ShoppBag> selectGuestShoppBag(Guest guest);//ShoppBag 테이블 리스트 보기 select 고객에 따라

    ShoppBag insertShoppBag(ShoppBag shoppBag); //ShoppBag 테이블에 값 추가 insert

    ShoppBag updateShoppBag(Long id, Guest guest,Orderr orderr, Menu menu, Integer sum) throws Exception;   //ShoppBag 테이블 수정 update


    void deleteShoppBag(Long id) throws Exception;   //ShoppBag 테이블 삭제 delete

    void deleteGuestShoppBag(Guest guest) throws Exception;   //ShoppBag 테이블 삭제 delete (고객에 따라 전체 삭제)
}
