package com.springboot.dbtask.controller;


import com.springboot.dbtask.data.dto.MenuDTO.MenuDto;
import com.springboot.dbtask.data.dto.MenuDTO.MenuFeatureNumberDto;
import com.springboot.dbtask.data.dto.MenuDTO.MenuResponseDto;
import com.springboot.dbtask.data.entity.Feature;
import com.springboot.dbtask.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;


    @GetMapping("/getAll")
    public ResponseEntity<List<MenuDto>> getAllMenu(){                //Menu 테이블 리스트 보기 select
        List<MenuDto> listsDto =  menuService.getAllMenu();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }

    @GetMapping("/getFeature/{feature}")
    public ResponseEntity<List<MenuFeatureNumberDto>> getAllMenu(@PathVariable Feature feature){               //Menu FeatureNumber 에 따라 테이블 리스트 보기 select
        List<MenuFeatureNumberDto> listsDto =  menuService.getFeatureNumberMenu(feature);
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }


    @PostMapping("/{name}/{price}/{feature}")
    public ResponseEntity<MenuResponseDto> createMenu(@PathVariable String name,
          @PathVariable Integer price,@PathVariable Feature feature) {       //Menu 테이블에 값 추가 insert

        MenuDto menuDto = new MenuDto();

        menuDto.setMenuName(name);
        menuDto.setMenuPrice(price);
        menuDto.setFeature(feature);

        MenuResponseDto menuResponseDto = menuService.saveMenu(menuDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuResponseDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }


    @PutMapping("/{id}/{name}/{price}/{feature}")
    public ResponseEntity<MenuResponseDto> updateMenuName(            //Menu 테이블 수정 update
           @PathVariable Long id,@PathVariable String name,@PathVariable Integer price,@PathVariable Feature feature) throws Exception {

        MenuResponseDto menuResponseDto = menuService.updateMenuName(id,name,price,feature);

        return ResponseEntity.status(HttpStatus.OK).body(menuResponseDto);

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long id) throws Exception {       //Menu 테이블 삭제 delete
        menuService.deleteMenu(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteAllMenu() throws Exception {       //Menu 테이블 삭제( 전체 ) delete
        menuService.deleteAllMenu();
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }



}
