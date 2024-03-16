package com.springboot.dbtask.controller;


import com.springboot.dbtask.data.dto.FeatureDTO.FeatureDto;
import com.springboot.dbtask.data.dto.FeatureDTO.FeatureResponseDto;
import com.springboot.dbtask.data.dto.GuestDTO.GuestDto;
import com.springboot.dbtask.data.dto.GuestDTO.GuestResponseDto;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.repository.GuestRepository;
import com.springboot.dbtask.service.FeatureService;
import com.springboot.dbtask.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    GuestService guestService;

    @PostMapping("/{name}")
    public ResponseEntity<GuestResponseDto> createGuest(@PathVariable String name) {       //Guest 테이블에 값 추가 insert

        GuestDto guestDto = new GuestDto();
        guestDto.setGuestName(name);
        GuestResponseDto guestResponseDto = guestService.saveGuest(guestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(guestResponseDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GuestDto>> getAllGuest(){                //Guest 테이블 리스트 보기 select
        List<GuestDto> listsDto =  guestService.getAllGuest();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }


    @PutMapping("/{id}/{name}")
    public ResponseEntity<GuestResponseDto> updateGuestName(            //Guest 테이블 수정 update
        @PathVariable Long id,@PathVariable String name) throws Exception {

        GuestResponseDto guestResponseDto = guestService.updateGuestName(id,name);

        return ResponseEntity.status(HttpStatus.OK).body(guestResponseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable Long id) throws Exception {       //Guest 테이블 삭제 delete
        guestService.deleteGuest(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }




}
