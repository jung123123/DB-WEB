package com.springboot.dbtask.data.dto.MenuDTO;

import com.springboot.dbtask.data.entity.Feature;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuDto {

    private Long MenuNumber;    //메뉴 번호

    private String MenuName;    //메뉴 이름

    private Integer MenuPrice;  //메뉴 가격

    private Feature feature;    //코너 번호

}
