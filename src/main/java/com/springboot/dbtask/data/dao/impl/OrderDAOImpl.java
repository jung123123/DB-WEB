package com.springboot.dbtask.data.dao.impl;

import com.springboot.dbtask.data.dao.OrderDAO;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Orderr;
import com.springboot.dbtask.data.entity.ShoppBag;
import com.springboot.dbtask.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDAOImpl implements OrderDAO {

    private OrderRepository orderRepository;

    @Autowired
    public OrderDAOImpl(OrderRepository orderRepository) {this.orderRepository = orderRepository;}


    @Override
    public List<Orderr> selectAllOrder() {        //Order 테이블 리스트 보기 select
        List<Orderr> list = orderRepository.findAll();
        return list;
    }

    @Override
    public List<Orderr> selectFoodOrder(Guest guest) {         //Order 테이블 리스트 보기 select (음식 주문 양식)
        List<Orderr> list = orderRepository.findByGuest(guest);
        return list;
    }

    @Override
    public Orderr insertOrder(Orderr orderr) {      //Order 테이블에 값 추가 insert
        Orderr save = orderRepository.save(orderr);
        return save;
    }

    @Override
    public Orderr updateOrder(Long id, Integer quantity, Guest guest, Menu menu) throws Exception {   //Order 테이블 수정 update
        Optional<Orderr> OrderOptional = orderRepository.findById(id);

        Orderr updateOrderr;
        if (OrderOptional.isPresent()) {
            Orderr orderr = OrderOptional.get();

            orderr.setOrderQuantity(quantity);
            orderr.setGuest(guest);
            orderr.setMenu(menu);

            updateOrderr = orderRepository.save(orderr);
        } else {
            throw new Exception();
        }

        return updateOrderr;
    }

    @Override
    public void deleteOrder(Long id) throws Exception {     //Order 테이블 삭제 delete
        Optional<Orderr> OrderOptional = orderRepository.findById(id);

        if (OrderOptional.isPresent()) {
            Orderr orderr = OrderOptional.get();

            orderRepository.delete(orderr);

        } else {
            throw new Exception();
        }
    }

    @Override
    public void deleteGuestOrder(Guest guest) throws Exception {     //Order 테이블 삭제 delete (고객에 따라 삭제)
        List<Orderr> orderrs = orderRepository.findByGuest(guest);

        if (!orderrs.isEmpty()) {
            for (Orderr orderr : orderrs) {
                orderRepository.delete(orderr);
            }
        } else {
            throw new Exception();
        }
    }

    @Override
    public void deleteAllOrder() throws Exception {
        List<Orderr> orderrs = orderRepository.findAll();

        if (!orderrs.isEmpty()) {
            orderRepository.deleteAll(orderrs);
        } else {
            throw new Exception("No orders found.");
        }
    }
}
