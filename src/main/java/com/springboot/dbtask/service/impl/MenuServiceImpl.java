package com.springboot.dbtask.service.impl;

import com.springboot.dbtask.data.dao.MenuDAO;
import com.springboot.dbtask.data.dto.MenuDTO.MenuDto;
import com.springboot.dbtask.data.dto.MenuDTO.MenuFeatureNumberDto;
import com.springboot.dbtask.data.dto.MenuDTO.MenuResponseDto;
import com.springboot.dbtask.data.entity.Feature;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuDAO menuDAO;

    @Autowired
    public MenuServiceImpl(MenuDAO menuDAO) {this.menuDAO = menuDAO;}


    @Override
    public MenuResponseDto saveMenu(MenuDto menuDto) {   //Menu 테이블에 값 추가 insert
        Menu menu = new Menu();

        menu.setMenuName(menuDto.getMenuName());
        menu.setMenuPrice(menuDto.getMenuPrice());
        menu.setFeature(menuDto.getFeature());

        Menu saveMenu = menuDAO.insertMenu(menu);

        MenuResponseDto menuResponseDto = new MenuResponseDto();

        menuResponseDto.setMenuNumber(saveMenu.getMenuNumber());
        menuResponseDto.setMenuName(saveMenu.getMenuName());
        menuResponseDto.setMenuPrice(saveMenu.getMenuPrice());
        menuResponseDto.setFeature(saveMenu.getFeature());

        return menuResponseDto;
    }

    @Override
    public List<MenuDto> getAllMenu() {     //Menu 테이블 리스트 보기 select
        List<Menu> list = menuDAO.selectAllMenu();

        List<MenuDto> listDto = new ArrayList<>();

        for (Menu m : list){

            MenuDto menuDto = new MenuDto();

            menuDto.setMenuNumber(m.getMenuNumber());
            menuDto.setMenuName(m.getMenuName());
            menuDto.setMenuPrice(m.getMenuPrice());
            menuDto.setFeature(m.getFeature());

            listDto.add(menuDto);

        }

        return listDto;
    }

    @Override
    public List<MenuFeatureNumberDto> getFeatureNumberMenu(Feature feature) {   //Menu FeatureNumber 에 따라 테이블 리스트 보기 select
        List<Menu> list = menuDAO.selectFeatureNumberMenu(feature);

        List<MenuFeatureNumberDto> listDto = new ArrayList<>();

        for (Menu m : list){

            MenuFeatureNumberDto menuDto = new MenuFeatureNumberDto();

            menuDto.setMenuNumber(m.getMenuNumber());
            menuDto.setMenuName(m.getMenuName());
            menuDto.setMenuPrice(m.getMenuPrice());
            menuDto.setFeatureNumber(m.getFeature().getFeatureNumber());

            listDto.add(menuDto);

        }

        return listDto;
    }

    @Override
    public MenuResponseDto updateMenuName(Long id, String name, Integer price, Feature feature) throws Exception {      //Menu 테이블 수정 update
        Menu updateMenu  = menuDAO.updateMenu(id,name,price,feature);

        MenuResponseDto menuResponseDto =  new MenuResponseDto();

        menuResponseDto.setMenuNumber(updateMenu.getMenuNumber());
        menuResponseDto.setMenuName(updateMenu.getMenuName());
        menuResponseDto.setMenuPrice(updateMenu.getMenuPrice());
        menuResponseDto.setFeature(updateMenu.getFeature());

        return menuResponseDto;
    }

    @Override
    public void deleteMenu(Long id) throws Exception {      //Menu 테이블 삭제 delete

        menuDAO.deleteMenu(id);
    }

    @Override
    public void deleteAllMenu() throws Exception {      //Menu 테이블 전체 삭제 delete
        menuDAO.deleteAllMenu();
    }
}
