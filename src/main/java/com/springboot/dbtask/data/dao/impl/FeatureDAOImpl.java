package com.springboot.dbtask.data.dao.impl;

import com.springboot.dbtask.data.dao.FeatureDAO;
import com.springboot.dbtask.data.entity.Feature;
import com.springboot.dbtask.data.repository.FeatureRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class FeatureDAOImpl implements FeatureDAO {

    private FeatureRepository featureRepository;

    private EntityManager entityManager;
    @Autowired
    public FeatureDAOImpl(FeatureRepository featureRepository, EntityManager entityManager) {
        this.featureRepository = featureRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Feature selectFeature(Long id) {             //Feature 테이블 리스트 보기 select (기본키 하나만 보기  -> 사용 안함)

        Feature select = featureRepository.getById(id);

        return select;
    }

    @Override
    public Feature insertFeature(Feature feature) {     //Feature 테이블에 값 추가 insert
        Feature save = featureRepository.save(feature);
        return save;
    }


    @Override
    public List<Feature> selectAllFeature() {       //Feature 테이블 리스트 보기 select
        List<Feature> list = featureRepository.findAll();
        return list;
    }

    @Override
    public Feature updateFeature(Long id, String name) throws Exception {       //Feature 테이블 수정 update
        Optional<Feature> featureOptional = featureRepository.findById(id);

        Feature updatedFeature;
        if (featureOptional.isPresent()) {
            Feature feature = featureOptional.get();

            feature.setFeatureName(name);

            updatedFeature = featureRepository.save(feature);
        } else {
            throw new Exception();
        }

        return updatedFeature;
    }


    @Override
    public void deleteFeature(Long id) throws Exception {       //Feature 테이블 삭제 delete
        Optional<Feature> featureOptional = featureRepository.findById(id);

        if (featureOptional.isPresent()) {
            Feature feature = featureOptional.get();

            featureRepository.delete(feature);

        } else {
            throw new Exception();
        }

    }

    @Transactional
    @Override
    public void deleteAllFeature() throws Exception {           //Feature 테이블  전체 삭제 delete
        featureRepository.deleteAll();
        // 외래 키 제약 조건 해제
        entityManager.createNativeQuery("ALTER TABLE menu DROP FOREIGN KEY FK3nes0805wo6nypebsgo8q4tgr")
                .executeUpdate();
        // feature 테이블 비우기
        entityManager.createNativeQuery("TRUNCATE TABLE feature").executeUpdate();

        // 외래 키 제약 조건 재설정
        entityManager.createNativeQuery("ALTER TABLE menu ADD CONSTRAINT FK3nes0805wo6nypebsgo8q4tgr " +
                        "FOREIGN KEY (feature_number) REFERENCES feature(feature_number)")
                .executeUpdate();
    }
}
