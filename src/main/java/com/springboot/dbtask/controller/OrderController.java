package com.springboot.dbtask.controller;




import com.springboot.dbtask.data.dto.OrderDTO.FoodOrderDto;
import com.springboot.dbtask.data.dto.OrderDTO.OrderDto;
import com.springboot.dbtask.data.dto.OrderDTO.OrderResponseDto;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> getAllOrder(){                //Order 테이블 리스트 보기 select
        List<OrderDto> listsDto =  orderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }


    @GetMapping("/getFood/{guest}")
    public ResponseEntity<List<FoodOrderDto>> getFoodOrder(@PathVariable Guest guest){                //Order 테이블 리스트 보기 select (음식 주문 양식)
        List<FoodOrderDto> listsDto =  orderService.getFoodOrder(guest);
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }

    @PostMapping("/{guest}/{menu}")
    public ResponseEntity<OrderResponseDto> createOrder(
            @PathVariable Guest guest,@PathVariable Menu menu) {       //Order 테이블에 값 추가 insert
                                                                        // 음식 초기 수량 1 개 라서 수량 따로 없음
        OrderDto orderDto = new OrderDto();


        orderDto.setGuest(guest);
        orderDto.setMenu(menu);


        OrderResponseDto orderResponseDto = orderService.saveOrder(orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }

    @PutMapping("/{id}/{quantity}/{guest}/{menu}")
    public ResponseEntity<OrderResponseDto> updateOrderName(            //Order 테이블 수정 update
           @PathVariable Long id,@PathVariable Integer quantity,
       @PathVariable Guest guest, @PathVariable Menu menu) throws Exception {

        OrderResponseDto orderResponseDto = orderService.updateOrder(id,quantity,guest,menu);

        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) throws Exception {       //Order 테이블 삭제 delete
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @DeleteMapping("/guest/{guest}")
    public ResponseEntity<String> deleteGuestOrder(@PathVariable Guest guest) throws Exception {       //Order 테이블 삭제 delete
        orderService.deleteGuestOrder(guest);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteGuestOrder() throws Exception {       //Order 테이블 삭제 delete
        orderService.deleteAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
