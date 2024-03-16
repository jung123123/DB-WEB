package com.springboot.dbtask.data.dto.GuestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestResponseDto {


    private Long GuestNumber;    //고객 번호

    private String GuestName;   //고객 이름
}
