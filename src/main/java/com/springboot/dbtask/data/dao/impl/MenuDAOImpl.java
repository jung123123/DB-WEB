package com.springboot.dbtask.data.dao.impl;

import com.springboot.dbtask.data.dao.MenuDAO;
import com.springboot.dbtask.data.entity.Feature;
import com.springboot.dbtask.data.entity.Menu;
import com.springboot.dbtask.data.repository.MenuRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MenuDAOImpl implements MenuDAO {

    private MenuRepository menuRepository;

    private EntityManager entityManager;
    @Autowired
    public MenuDAOImpl(MenuRepository menuRepository, EntityManager entityManager) {
        this.menuRepository = menuRepository;
        this.entityManager = entityManager;
    }


    @Override
    public List<Menu> selectAllMenu() {     //Menu 테이블 리스트 보기 select
        List<Menu> list = menuRepository.findAll();
        return list;
    }

    @Override
    public List<Menu> selectFeatureNumberMenu(Feature feature) {    //Menu FeatureNumber 에 따라 테이블 리스트 보기 select
        List<Menu> list = menuRepository.findByFeature(feature);
        return list;
    }

    @Override
    public Menu insertMenu(Menu menu) {     //Menu 테이블에 값 추가 insert
        Menu save = menuRepository.save(menu);
        return save;
    }

    @Override
    public Menu updateMenu(Long id, String name, Integer price, Feature feature) throws Exception {      //Menu 테이블 수정 update
        Optional<Menu> MenuOptional = menuRepository.findById(id);

        Menu updateMenu;
        if (MenuOptional.isPresent()) {
            Menu menu = MenuOptional.get();

            menu.setMenuName(name);
            menu.setMenuPrice(price);
            menu.setFeature(feature);

            updateMenu = menuRepository.save(menu);
        } else {
            throw new Exception();
        }

        return updateMenu;
    }

    @Override
    public void deleteMenu(Long id) throws Exception {          //Menu 테이블 삭제 delete
        Optional<Menu> MenuOptional = menuRepository.findById(id);

        if (MenuOptional.isPresent()) {
            Menu menu = MenuOptional.get();

            menuRepository.delete(menu);

        } else {
            throw new Exception();
        }
    }

    @Transactional
    @Override
    public void deleteAllMenu() throws Exception {
        menuRepository.deleteAll();
        // 외래 키 제약 조건 해제
        entityManager.createNativeQuery("ALTER TABLE `orderr` DROP FOREIGN KEY FK2mcna2cuomtjnar6t8j9i6bad")
                .executeUpdate();

        entityManager.createNativeQuery("ALTER TABLE `shoppbag` DROP FOREIGN KEY FK8b91n0461j82cjian3hu8eath")
                .executeUpdate();
        // menu 테이블 비우기, shoppbag 테이블 비우기
        entityManager.createNativeQuery("TRUNCATE TABLE menu").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE shoppbag").executeUpdate();

        // 외래 키 제약 조건 재설정
        entityManager.createNativeQuery("ALTER TABLE `orderr` ADD CONSTRAINT FK2mcna2cuomtjnar6t8j9i6bad " +
                        "FOREIGN KEY (menu_number) REFERENCES menu(menu_number)")
                .executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE `shoppbag` ADD CONSTRAINT FK8b91n0461j82cjian3hu8eath " +
                        "FOREIGN KEY (menu_number) REFERENCES menu(menu_number)")
                .executeUpdate();
    }
}
