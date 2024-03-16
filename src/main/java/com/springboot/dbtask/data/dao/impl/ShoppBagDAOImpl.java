package com.springboot.dbtask.data.dao.impl;

import com.springboot.dbtask.data.dao.ShoppBagDAO;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Orderr;
import com.springboot.dbtask.data.entity.ShoppBag;
import com.springboot.dbtask.data.repository.ShoppBagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ShoppBagDAOImpl implements ShoppBagDAO {

    private ShoppBagRepository shoppBagRepository;

    @Autowired
    public ShoppBagDAOImpl(ShoppBagRepository shoppBagRepository) {this.shoppBagRepository = shoppBagRepository;}

    @Override
    public List<ShoppBag> selectAllShoppBag() {     //ShoppBag 테이블 리스트 보기 select
        List<ShoppBag> list = shoppBagRepository.findAll();
        return list;
    }

    @Override
    public List<ShoppBag> selectGuestShoppBag(Guest guest) {
        List<ShoppBag> list = shoppBagRepository.findByGuest(guest);
        return list;
    }

    @Override
    public ShoppBag insertShoppBag(ShoppBag shoppBag) { //ShoppBag 테이블에 값 추가 insert
        ShoppBag save = shoppBagRepository.save(shoppBag);
        return save;
    }

    @Override
    public ShoppBag updateShoppBag(Long id, Guest guest, Orderr orderr, Menu menu, Integer sum) throws Exception {        //ShoppBag 테이블 수정 update
        Optional<ShoppBag> ShoppBagOptional = shoppBagRepository.findById(id);

        ShoppBag updateShoppBag;
        if (ShoppBagOptional.isPresent()) {
            ShoppBag shoppBag = ShoppBagOptional.get();
            shoppBag.setGuest(guest);
            shoppBag.setOrderr(orderr);
            shoppBag.setMenu(menu);
            shoppBag.setSum(sum);

            updateShoppBag = shoppBagRepository.save(shoppBag);
        } else {
            throw new Exception();
        }

        return updateShoppBag;
    }

    @Override
    public void deleteShoppBag(Long id) throws Exception {      //ShoppBag 테이블 삭제 delete
        Optional<ShoppBag> ShoppBagOptional = shoppBagRepository.findById(id);

        if (ShoppBagOptional.isPresent()) {
            ShoppBag shoppBag = ShoppBagOptional.get();

            shoppBagRepository.delete(shoppBag);

        } else {
            throw new Exception();
        }

    }


    @Override
    public void deleteGuestShoppBag(Guest guest) throws Exception {     //ShoppBag 테이블 삭제 delete 고객에 따라 삭제
        List<ShoppBag> shoppBags = shoppBagRepository.findByGuest(guest);

        if (!shoppBags.isEmpty()) {
            for (ShoppBag shoppBag : shoppBags) {
                shoppBagRepository.delete(shoppBag);
            }
        } else {
            throw new Exception();
        }
    }
}
