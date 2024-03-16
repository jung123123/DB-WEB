package com.springboot.dbtask.service.impl;



import com.springboot.dbtask.data.dao.ShoppBagDAO;
import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagDto;
import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagResponseDto;
import com.springboot.dbtask.data.dto.ShoppBagDTO.ShoppBagSumDto;
import com.springboot.dbtask.data.entity.Guest;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.entity.Orderr;
import com.springboot.dbtask.data.entity.ShoppBag;
import com.springboot.dbtask.service.ShoppBagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppBagServiceImpl implements ShoppBagService {


    private final ShoppBagDAO shoppBagDAO;
    @Autowired
    public ShoppBagServiceImpl(ShoppBagDAO shoppBagDAO) {this.shoppBagDAO = shoppBagDAO;}

    @Override
    public ShoppBagResponseDto saveShoppBag(ShoppBagDto shoppBagDto) {      //ShoppBag 테이블에 값 추가 insert
        ShoppBag shoppBag = new ShoppBag();

        shoppBag.setGuest(shoppBagDto.getGuest());
        shoppBag.setOrderr(shoppBagDto.getOrderr());
        shoppBag.setMenu(shoppBagDto.getMenu());
        shoppBag.setSum(shoppBagDto.getSum());


        ShoppBag saveShoppBag = shoppBagDAO.insertShoppBag(shoppBag);

        ShoppBagResponseDto shoppBagResponseDto = new ShoppBagResponseDto();

        shoppBagResponseDto.setShoppBagNumber(saveShoppBag.getShoppBagNumber());
        shoppBagResponseDto.setGuest(saveShoppBag.getGuest());
        shoppBagResponseDto.setOrderr(saveShoppBag.getOrderr());
        shoppBagResponseDto.setMenu(saveShoppBag.getMenu());


        return shoppBagResponseDto;
    }

    @Override
    public List<ShoppBagDto> getAllShoppBag() {              //ShoppBag 테이블 리스트 보기 select
        List<ShoppBag> list = shoppBagDAO.selectAllShoppBag();

        List<ShoppBagDto> listDto = new ArrayList<>();

        for (ShoppBag s : list){

            ShoppBagDto shoppBagDto = new ShoppBagDto();

            shoppBagDto.setShoppBagNumber(s.getShoppBagNumber());
            shoppBagDto.setGuest(s.getGuest());
            shoppBagDto.setOrderr(s.getOrderr());
            shoppBagDto.setMenu(s.getMenu());
            shoppBagDto.setSum(s.getSum());

            listDto.add(shoppBagDto);

        }

        return listDto;
    }


    @Override
    public List<ShoppBagSumDto> getGuestShoppBag(Guest guest) {
        List<ShoppBag> list = shoppBagDAO.selectGuestShoppBag(guest);

        List<ShoppBagSumDto> listDto = new ArrayList<>();

        int sum = 0;

        for (ShoppBag s : list){

            ShoppBagSumDto shoppBagSumDto = new ShoppBagSumDto();


            shoppBagSumDto.setGuestNumber(s.getGuest().getGuestNumber());
            shoppBagSumDto.setMenuName(s.getMenu().getMenuName());
            shoppBagSumDto.setOrderQuantity(s.getOrderr().getOrderQuantity());
            shoppBagSumDto.setMenuPrice(s.getMenu().getMenuPrice());

            sum += s.getOrderr().getOrderQuantity()*s.getMenu().getMenuPrice();
            System.out.println(sum);

            shoppBagSumDto.setSum(sum);

            listDto.add(shoppBagSumDto);

        }

        return listDto;
    }

    @Override
    public ShoppBagResponseDto updateShoppBag(Long id, Guest guest, Orderr orderr, Menu menu, Integer sum) throws Exception {        //ShoppBag 테이블 수정 update
        ShoppBag updateShoppBag = shoppBagDAO.updateShoppBag(id,guest,orderr,menu,sum);

        ShoppBagResponseDto shoppBagResponseDto =  new ShoppBagResponseDto();

        shoppBagResponseDto.setGuest(updateShoppBag.getGuest());
        shoppBagResponseDto.setOrderr(updateShoppBag.getOrderr());
        shoppBagResponseDto.setMenu(updateShoppBag.getMenu());
        shoppBagResponseDto.setSum(updateShoppBag.getSum());

        return shoppBagResponseDto;
    }

    @Override
    public void deleteShoppBag(Long id) throws Exception {      //ShoppBag 테이블 삭제 delete
        shoppBagDAO.deleteShoppBag(id);
    }

    @Override
    public void deleteGuestShoppBag(Guest guest) throws Exception {     //ShoppBag 테이블 삭제 delete 고객에 따라
        shoppBagDAO.deleteGuestShoppBag(guest);
    }
}
