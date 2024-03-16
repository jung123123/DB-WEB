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
@Table(name = "menu") //테이블 이름
public class Menu {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    private Long MenuNumber;   //메뉴 번호

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")   //null 값 금지, 최대 길이 50
    private String MenuName;    //메뉴 이름

    @Column(nullable = false)   //null 값 금지
    private Integer MenuPrice;  //메뉴 가격

    @ManyToOne      //단방향
    @JoinColumn(name = "FeatureNumber") //fk
    private Feature feature;            //코너 번호

}
