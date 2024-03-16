package com.springboot.dbtask.data.dao.impl;

import com.springboot.dbtask.data.dao.GuestDAO;
import com.springboot.dbtask.data.entity.Feature;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.repository.FeatureRepository;
import com.springboot.dbtask.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GuestDAOImpl implements GuestDAO {

    private GuestRepository guestRepository;

    @Autowired
    public GuestDAOImpl(GuestRepository guestRepository) {this.guestRepository = guestRepository;}

    @Override
    public List<Guest> selectAllGuest() {   //Guest 테이블 리스트 보기 select
        List<Guest> list = guestRepository.findAll();
        return list;
    }

    @Override
    public Guest insertGuest(Guest guest) {     //Guest 테이블에 값 추가 insert
        Guest save = guestRepository.save(guest);
        return save;
    }

    @Override
    public Guest updateGuest(Long id, String name) throws Exception {       //Guest 테이블 수정 update
        Optional<Guest> GuestOptional = guestRepository.findById(id);

        Guest updateGuest;
        if (GuestOptional.isPresent()) {
            Guest guest = GuestOptional.get();

            guest.setGuestName(name);

            updateGuest = guestRepository.save(guest);
        } else {
            throw new Exception();
        }

        return updateGuest;
    }

    @Override
    public void deleteGuest(Long id) throws Exception {     //Guest 테이블 삭제 delete
        Optional<Guest> GuestOptional = guestRepository.findById(id);

        if (GuestOptional.isPresent()) {
            Guest guest = GuestOptional.get();

            guestRepository.delete(guest);

        } else {
            throw new Exception();
        }
    }
}
