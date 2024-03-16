package com.springboot.dbtask.service;


import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagDto;
import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagResponseDto;
import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagSumDto;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Orderr;

import java.util.List;

public interface ShoppBagService {
    // Service 와 DAO 를 인터페이스로 만드는 것은 유연성, 확장성, 유지보수성, 테스트 용이성 등을 향상시키기 위함
    // Controller 의 요청에 맞추어 Repository 에서 받은 정보를 가공하여 Controller 에게 넘겨주는 비지니스 로직


    ShoppBagResponseDto saveShoppBag(ShoppBagDto shoppBagDto);  //ShoppBag 테이블에 값 추가 insert

    List<ShoppBagDto> getAllShoppBag();       //ShoppBag 테이블 리스트 보기 select

    List<ShoppBagSumDto> getGuestShoppBag(Guest guest);       //ShoppBag 테이블 리스트 보기 select


    ShoppBagResponseDto updateShoppBag(Long id, Guest guest, Orderr orderr, Menu menu, Integer sum) throws Exception;        //ShoppBag 테이블 수정 update

    void deleteShoppBag(Long id) throws Exception;       //ShoppBag 테이블 삭제 delete

    void deleteGuestShoppBag(Guest guest) throws Exception;       //ShoppBag 테이블 삭제 delete  고객에 따라
}
