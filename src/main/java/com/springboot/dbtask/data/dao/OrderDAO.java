package com.springboot.dbtask.data.dao;

import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Orderr;
import com.springboot.dbtask.data.entity.Menu;

import java.util.List;

public interface OrderDAO {
    // Service와 DAO를 인터페이스로 만드는 것은 유연성, 확장성, 유지보수성, 테스트 용이성 등을 향상시키기 위함

    List<Orderr> selectAllOrder();       //Order 테이블 리스트 보기 select

    List<Orderr> selectFoodOrder(Guest guest);     //Order 테이블 리스트 보기 select (음식 주문 양식)



    Orderr insertOrder(Orderr orderr); //Order 테이블에 값 추가 insert

    Orderr updateOrder(Long id, Integer quantity, Guest guest, Menu menu) throws Exception;   //Order 테이블 수정 update


    void deleteOrder(Long id) throws Exception;   //Order 테이블 삭제 delete

    void deleteGuestOrder(Guest guest) throws Exception;   //Order 테이블 삭제 guest 에 따라 elete

    void deleteAllOrder() throws Exception;   //Order 테이블 모두 삭제 delete
}
