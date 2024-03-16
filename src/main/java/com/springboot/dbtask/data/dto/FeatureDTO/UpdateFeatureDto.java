package com.springboot.dbtask.data.dto.FeatureDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFeatureDto {


    private Long FeatureNumber;  //코너 번호

    private String FeatureName;  //코너 이름

}
