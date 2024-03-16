package com.springboot.dbtask.data.dao;

import com.springboot.dbtask.data.entity.Feature;
import com.springboot.dbtask.data.entity.Menu;

import java.util.List;

public interface MenuDAO {
    // Service와 DAO를 인터페이스로 만드는 것은 유연성, 확장성, 유지보수성, 테스트 용이성 등을 향상시키기 위함

    List<Menu> selectAllMenu();       //Menu 테이블 리스트 보기 select

    List<Menu> selectFeatureNumberMenu(Feature feature);    //Menu FeatureNumber 에 따라 테이블 리스트 보기 select

    Menu insertMenu(Menu menu); //Menu 테이블에 값 추가 insert

    Menu updateMenu(Long id, String name, Integer price,Feature feature) throws Exception;   //Menu 테이블 수정 update


    void deleteMenu(Long id) throws Exception;   //Menu 테이블 삭제 delete

    void deleteAllMenu() throws Exception;   //Menu 테이블 All 삭제 delete
}
