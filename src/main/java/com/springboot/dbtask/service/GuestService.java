package com.springboot.dbtask.service;

import com.springboot.dbtask.data.dto.FeatureDTO.FeatureDto;
import com.springboot.dbtask.data.dto.FeatureDTO.FeatureResponseDto;
import com.springboot.dbtask.data.dto.GuestDTO.GuestDto;
import com.springboot.dbtask.data.dto.GuestDTO.GuestResponseDto;

import java.util.List;

public interface GuestService {
    // Service 와 DAO 를 인터페이스로 만드는 것은 유연성, 확장성, 유지보수성, 테스트 용이성 등을 향상시키기 위함
    // Controller 의 요청에 맞추어 Repository 에서 받은 정보를 가공하여 Controller 에게 넘겨주는 비지니스 로직


    GuestResponseDto saveGuest(GuestDto guestDto);  //Guest 테이블에 값 추가 insert

    List<GuestDto> getAllGuest();       //Guest 테이블 리스트 보기 select

    GuestResponseDto updateGuestName(Long id, String name) throws Exception;        //Guest 테이블 수정 update

    void deleteGuest(Long id) throws Exception;       //Guest 테이블 삭제 delete


}
