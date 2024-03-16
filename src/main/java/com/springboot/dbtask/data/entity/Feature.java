package com.springboot.dbtask.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feature") //테이블 이름
public class Feature {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    private Long FeatureNumber; //코너 번호

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")   //null 값 금지, 최대 길이 50
    private String FeatureName; //코너 이름

}
