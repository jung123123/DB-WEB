package com.springboot.dbtask.service.impl;


import com.springboot.dbtask.data.dao.GuestDAO;
import com.springboot.dbtask.data.dto.GuestDTO.GuestDto;
import com.springboot.dbtask.data.dto.GuestDTO.GuestResponseDto;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service    //DB에 접근하는 코드는 repository 에 위임      비즈니스 로직과 관련된 모든 코드
public class GuestServiceImpl implements GuestService {


    private final GuestDAO guestDAO;

    @Autowired
    public GuestServiceImpl(GuestDAO guestDAO) {this.guestDAO = guestDAO;}

    @Override
    public GuestResponseDto saveGuest(GuestDto guestDto) {          //Guest 테이블에 값 추가 insert
        Guest guest = new Guest();

        guest.setGuestName(guestDto.getGuestName());

        Guest saveGuest = guestDAO.insertGuest(guest);

        GuestResponseDto guestResponseDto = new GuestResponseDto();

        guestResponseDto.setGuestName(saveGuest.getGuestName());
        guestResponseDto.setGuestNumber(saveGuest.getGuestNumber());

        return guestResponseDto;
    }

    @Override
    public List<GuestDto> getAllGuest() {               //Guest 테이블 리스트 보기 select
        List<Guest> list = guestDAO.selectAllGuest();

        List<GuestDto> listDto = new ArrayList<>();

        for (Guest g : list){

            GuestDto guestDto = new GuestDto();

            guestDto.setGuestNumber(g.getGuestNumber());
            guestDto.setGuestName(g.getGuestName());

            listDto.add(guestDto);

        }

        return listDto;
    }

    @Override
    public GuestResponseDto updateGuestName(Long id, String name) throws Exception {       //Guest 테이블 수정 update
        Guest updateGuest  = guestDAO.updateGuest(id,name);

        GuestResponseDto guestResponseDto =  new GuestResponseDto();

        guestResponseDto.setGuestNumber(updateGuest.getGuestNumber());
        guestResponseDto.setGuestName(updateGuest.getGuestName());


        return guestResponseDto;
    }

    @Override
    public void deleteGuest(Long id) throws Exception {     //Guest 테이블 삭제 delete

        guestDAO.deleteGuest(id);


    }
}
