package com.springboot.dbtask.service;

import com.springboot.dbtask.data.dto.FeatureDTO.FeatureDto;
import com.springboot.dbtask.data.dto.FeatureDTO.FeatureResponseDto;

import java.util.List;

public interface FeatureService {


    FeatureDto getFeature(Long FeatureNumber);  // 사용 안함 // Feature 테이블 리스트 보기 select  (번호로 하나)

    FeatureResponseDto saveFeature(FeatureDto featureDto);  //Feature 테이블에 값 추가 insert

    List<FeatureDto> getAllFeature();       //Feature 테이블 리스트 보기 select

    FeatureResponseDto updateFeatureName(Long id, String name) throws Exception;        //Feature 테이블 수정 update

    void deleteFeature(Long id) throws Exception;       //Feature 테이블 삭제 delete


    void deleteAllFeature() throws Exception;       //Feature 테이블 삭제 delete     전체 삭제




}
