package com.springboot.dbtask.data.dao;

import com.springboot.dbtask.data.entity.Feature;

import java.util.List;

public interface FeatureDAO {


    Feature selectFeature(Long FeatureNumber);

    List<Feature> selectAllFeature();       //Feature 테이블 리스트 보기 select

    Feature insertFeature(Feature feature); //Feature 테이블에 값 추가 insert

    Feature updateFeature(Long id, String name) throws Exception;   //Feature 테이블 수정 update


    void deleteFeature(Long id) throws Exception;   //Feature 테이블 삭제 delete

    void deleteAllFeature() throws Exception;   //Feature 테이블 All 삭제 delete

}
