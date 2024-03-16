package com.springboot.dbtask.data.dto.OrderDTO;

import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDto {

    private Long OrderNumber;   //주문 번호

    private Integer OrderQuantity; //주문 수량

    private String MenuName;    //메뉴 이름

    private Integer MenuPrice;  //메뉴 가격
    
    private Long menuNumber; //메뉴 번호

    private Guest guest;    //고객 번호

}
