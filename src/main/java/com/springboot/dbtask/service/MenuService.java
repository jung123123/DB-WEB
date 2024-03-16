package com.springboot.dbtask.service;

import com.springboot.dbtask.data.dto.MenuDTO.MenuDto;
import com.springboot.dbtask.data.dto.MenuDTO.MenuFeatureNumberDto;
import com.springboot.dbtask.data.dto.MenuDTO.MenuResponseDto;
import com.springboot.dbtask.data.entity.Feature;

import java.util.List;

public interface MenuService {
    // Service 와 DAO 를 인터페이스로 만드는 것은 유연성, 확장성, 유지보수성, 테스트 용이성 등을 향상시키기 위함
    // Controller 의 요청에 맞추어 Repository 에서 받은 정보를 가공하여 Controller 에게 넘겨주는 비지니스 로직


    MenuResponseDto saveMenu(MenuDto menuDto);  //Menu 테이블에 값 추가 insert

    List<MenuDto> getAllMenu();       //Menu 테이블 리스트 보기 select

    List<MenuFeatureNumberDto> getFeatureNumberMenu(Feature feature);       //Menu FeatureNumber 에 따라 테이블 리스트 보기 select

    MenuResponseDto updateMenuName(Long id, String name, Integer price, Feature feature) throws Exception;        //Menu 테이블 수정 update

    void deleteMenu(Long id) throws Exception;       //Menu 테이블 삭제 delete

    void deleteAllMenu() throws Exception;       //Menu 테이블 삭제 delete       전체 삭제
}
