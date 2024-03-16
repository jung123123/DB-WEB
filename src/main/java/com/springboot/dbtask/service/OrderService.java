package com.springboot.dbtask.service;


import com.springboot.dbtask.data.dto.OrderDTO.FoodOrderDto;
import com.springboot.dbtask.data.dto.OrderDTO.OrderDto;
import com.springboot.dbtask.data.dto.OrderDTO.OrderResponseDto;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Guest;

import java.util.List;

public interface OrderService {
    // Service 와 DAO 를 인터페이스로 만드는 것은 유연성, 확장성, 유지보수성, 테스트 용이성 등을 향상시키기 위함
    // Controller 의 요청에 맞추어 Repository 에서 받은 정보를 가공하여 Controller 에게 넘겨주는 비지니스 로직


    OrderResponseDto saveOrder(OrderDto orderDto);  //Order 테이블에 값 추가 insert

    List<OrderDto> getAllOrder();       //Order 테이블 리스트 보기 select

    List<FoodOrderDto> getFoodOrder(Guest guest);      //Order 테이블 리스트 보기 select (음식 주문 양식)


    OrderResponseDto updateOrder(Long id, Integer quantity, Guest guest, Menu menu) throws Exception;        //Order 테이블 수정 update

    void deleteOrder(Long id) throws Exception;       //Order 테이블 삭제 delete

    void deleteGuestOrder(Guest guest) throws Exception;       //Order 테이블 삭제 delete    고객 에 따라

    void deleteAllOrder() throws Exception;       //Order 테이블 모두 삭제 delete
}
