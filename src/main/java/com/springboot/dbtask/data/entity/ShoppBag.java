package com.springboot.dbtask.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shoppbag") //테이블 이름
public class ShoppBag  {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    private Long ShoppBagNumber;

    @ManyToOne  //단방향
    @JoinColumn(name = "GuestNumber") //fk
    private Guest guest;                //고객 번호


    @ManyToOne  //단방향
    @JoinColumn(name = "OrderNumber") //fk
    private Orderr orderr;              //주문 번호

    @ManyToOne  //단방향
    @JoinColumn(name = "MenuNumber") //fk
    private Menu menu;                  //메뉴 번호
    
    private Integer sum;    //총합 가격

}
