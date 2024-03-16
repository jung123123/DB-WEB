package com.springboot.dbtask.data.dto.ShoppBagDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppBagSumDto {

    private Long guestNumber;       //고객 번호

    private String MenuName;    //메뉴 이름
    private Integer menuPrice;      // 메뉴가격

    private Integer OrderQuantity;  //메뉴 수량

    private Integer sum;            // 총합

}
