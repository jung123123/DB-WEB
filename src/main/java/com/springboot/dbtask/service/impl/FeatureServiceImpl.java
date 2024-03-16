package com.springboot.dbtask.service.impl;

import com.springboot.dbtask.data.dao.FeatureDAO;
import com.springboot.dbtask.data.dto.FeatureDTO.FeatureDto;
import com.springboot.dbtask.data.dto.FeatureDTO.FeatureResponseDto;
import com.springboot.dbtask.data.entity.Feature;
import com.springboot.dbtask.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureService {


    private final FeatureDAO featureDAO;

    @Autowired
    public FeatureServiceImpl(FeatureDAO featureDAO) {this.featureDAO = featureDAO;}



    @Override
    public FeatureDto getFeature(Long id) {     //Feature 테이블 리스트 보기 select id 하나

        Feature feature = featureDAO.selectFeature(id);

        FeatureDto featureDto = new FeatureDto();
        featureDto.setFeatureName(feature.getFeatureName());

        return featureDto;
    }

    @Override
    public FeatureResponseDto saveFeature(FeatureDto featureDto) {  //Feature 테이블에 값 추가 insert
        Feature feature = new Feature();

        feature.setFeatureName(featureDto.getFeatureName());

        Feature saveFeature = featureDAO.insertFeature(feature);

        FeatureResponseDto featureResponseDto = new FeatureResponseDto();
        featureResponseDto.setFeatureNumber(saveFeature.getFeatureNumber());
        featureResponseDto.setFeatureName(saveFeature.getFeatureName());

        return featureResponseDto;
    }


    @Override
    public List<FeatureDto> getAllFeature() {       //Feature 테이블 리스트 보기 select
        List<Feature> list = featureDAO.selectAllFeature();

        List<FeatureDto> listDto = new ArrayList<>();

        for (Feature f : list){
            FeatureDto featureDto = new FeatureDto();
            featureDto.setFeatureNumber(f.getFeatureNumber());
            featureDto.setFeatureName(f.getFeatureName());

            listDto.add(featureDto);

        }

        return listDto;
    }

    @Override
    public FeatureResponseDto updateFeatureName(Long id, String name) throws Exception {        //Feature 테이블 수정 update

        Feature updateFeature = featureDAO.updateFeature(id,name);

        FeatureResponseDto featureResponseDto =  new FeatureResponseDto();
        featureResponseDto.setFeatureNumber(updateFeature.getFeatureNumber());
        featureResponseDto.setFeatureName(updateFeature.getFeatureName());


        return featureResponseDto;
    }


    @Override
    public void deleteFeature(Long id) throws Exception {       //Feature 테이블 삭제 delete
        featureDAO.deleteFeature(id);
    }

    @Override
    public void deleteAllFeature() throws Exception {           //Feature 테이블 전체 삭제 delete
        featureDAO.deleteAllFeature();
    }
}
