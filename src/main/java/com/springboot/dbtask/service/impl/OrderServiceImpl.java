package com.springboot.dbtask.service.impl;

import com.springboot.dbtask.data.dao.MenuDAO;
import com.springboot.dbtask.data.dao.OrderDAO;
import com.springboot.dbtask.data.dto.OrderDTO.FoodOrderDto;
import com.springboot.dbtask.data.dto.OrderDTO.OrderDto;
import com.springboot.dbtask.data.dto.OrderDTO.OrderResponseDto;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Orderr;
import com.springboot.dbtask.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {this.orderDAO = orderDAO;}


    @Override
    public OrderResponseDto saveOrder(OrderDto orderDto) {      //Order 테이블에 값 추가 insert

        Orderr orderr = new Orderr();
        orderr.setOrderQuantity(1);         //초기 수량 1 개
        orderr.setMenu(orderDto.getMenu());
        orderr.setGuest(orderDto.getGuest());


        Orderr saveOrderr = orderDAO.insertOrder(orderr);

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setOrderNumber(saveOrderr.getOrderNumber());
        orderResponseDto.setOrderQuantity(saveOrderr.getOrderQuantity());
        orderResponseDto.setGuest(saveOrderr.getGuest());
        orderResponseDto.setMenu(saveOrderr.getMenu());


        return orderResponseDto;
    }


    @Override
    public List<OrderDto> getAllOrder() {       //Order 테이블 리스트 보기 select
        List<Orderr> list = orderDAO.selectAllOrder();

        List<OrderDto> listDto = new ArrayList<>();

        for (Orderr o : list){

            OrderDto orderDto = new OrderDto();

            orderDto.setOrderNumber(o.getOrderNumber());
            orderDto.setOrderQuantity(o.getOrderQuantity());
            orderDto.setGuest(o.getGuest());
            orderDto.setMenu(o.getMenu());


            listDto.add(orderDto);

        }

        return listDto;
    }

    @Override
    public List<FoodOrderDto> getFoodOrder(Guest guest) {                  //Order 테이블 리스트 보기 select (음식 주문 양식)
        List<Orderr> list = orderDAO.selectFoodOrder(guest);

        List<FoodOrderDto> listDto = new ArrayList<>();



        for (Orderr o : list){

            FoodOrderDto foodOrderDto = new FoodOrderDto();

            foodOrderDto.setOrderNumber(o.getOrderNumber());
            if(o.getOrderQuantity() == null) {
                foodOrderDto.setOrderQuantity(1);// 초기 수량 1개
            }else{
                foodOrderDto.setOrderQuantity(o.getOrderQuantity());
            }
            foodOrderDto.setMenuName(o.getMenu().getMenuName());
            foodOrderDto.setMenuPrice(o.getMenu().getMenuPrice());
            foodOrderDto.setMenuNumber(o.getMenu().getMenuNumber());

            listDto.add(foodOrderDto);

        }


        return listDto;
    }

    @Override
    public OrderResponseDto updateOrder(Long id, Integer quantity, Guest guest, Menu menu) throws Exception {        //Order 테이블 수정 update
        Orderr updateOrderr = orderDAO.updateOrder(id,quantity,guest,menu);

        OrderResponseDto orderResponseDto =  new OrderResponseDto();

        orderResponseDto.setOrderNumber(updateOrderr.getOrderNumber());
        orderResponseDto.setOrderQuantity(updateOrderr.getOrderQuantity());
        orderResponseDto.setGuest(updateOrderr.getGuest());
        orderResponseDto.setMenu(updateOrderr.getMenu());


        return orderResponseDto;
    }

    @Override
    public void deleteOrder(Long id) throws Exception {     //Order 테이블 삭제 delete
        orderDAO.deleteOrder(id);
    }

    @Override
    public void deleteGuestOrder(Guest guest) throws Exception {        //Order 테이블 삭제 delete  고객에 따라
        orderDAO.deleteGuestOrder(guest);
    }

    @Override
    public void deleteAllOrder() throws Exception {
        orderDAO.deleteAllOrder();
    }
}
