package com.springboot.dbtask.data.dto.ShoppBagDTO;

import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Orderr;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppBagResponseDto {

    private Long ShoppBagNumber;    //쇼핑백 번호
    private Guest guest;        //고객 번호
    private Orderr orderr;    //주문 번호

    private Menu menu;      // 메뉴 번호
    
    private Integer sum;    //총합


}
