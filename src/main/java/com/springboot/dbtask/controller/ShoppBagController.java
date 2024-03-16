package com.springboot.dbtask.controller;


import com.querydsl.jpa.impl.JPAQuery;
import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagDto;
import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagResponseDto;
import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagSumDto;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Orderr;
import com.springboot.dbtask.data.entity.ShoppBag;
import com.springboot.dbtask.service.ShoppBagService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppBag")
public class ShoppBagController {

    @Autowired
    ShoppBagService shoppBagService;



    @GetMapping("/getAll")
    public ResponseEntity<List<ShoppBagDto>> getAllShoppBag(){                //ShoppBag 테이블 리스트 보기 select
        List<ShoppBagDto> listsDto =  shoppBagService.getAllShoppBag();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }

    @GetMapping("/getSum/{guest}")
    public ResponseEntity<List<ShoppBagSumDto>> getSumShoppBag(@PathVariable Guest guest){                //ShoppBag 테이블 리스트 보기  select 고객에 따라 총합액 보기
        List<ShoppBagSumDto> listsDto =  shoppBagService.getGuestShoppBag(guest);


        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }



    @PostMapping("/{guest}/{orderr}/{menu}")
    public ResponseEntity<ShoppBagResponseDto> createShoppBag(@PathVariable Guest guest,
         @PathVariable Orderr orderr, @PathVariable Menu menu) {       //ShoppBag 테이블에 값 추가 insert

        ShoppBagDto shoppBagDto = new ShoppBagDto();

        shoppBagDto.setGuest(guest);
        shoppBagDto.setOrderr(orderr);
        shoppBagDto.setMenu(menu);


        ShoppBagResponseDto shoppBagResponseDto = shoppBagService.saveShoppBag(shoppBagDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(shoppBagResponseDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }

    @PutMapping("/{id}/{guest}/{orderr}/{menu}/{sum}")
    public ResponseEntity<ShoppBagResponseDto> updateShoppBagName(            //ShoppBag 테이블 수정 update
      @PathVariable Long id,@PathVariable Guest guest,@PathVariable Orderr orderr,
      @PathVariable Menu menu, @PathVariable Integer sum) throws Exception {

        ShoppBagResponseDto shoppBagResponseDto = shoppBagService.updateShoppBag(id,guest,orderr,menu,sum);

        return ResponseEntity.status(HttpStatus.OK).body(shoppBagResponseDto);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShoppBag(@PathVariable Long id) throws Exception {       //ShoppBag 테이블 삭제 delete
                shoppBagService.deleteShoppBag(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @DeleteMapping("/guest/{guest}")
    public ResponseEntity<String> deleteGuestShoppBag(@PathVariable Guest guest) throws Exception {       //ShoppBag 테이블 삭제 delete
        shoppBagService.deleteGuestShoppBag(guest);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
