package com.springboot.dbtask.data.dto.OrderDTO;

import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

    private Long OrderNumber;   //주문 번호

    private Integer OrderQuantity; //주문 수량

    private Guest guest;              //고객 번호

    private Menu menu;              // 메뉴 번호

}
