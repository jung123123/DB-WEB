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
@Table(name = "orderr") //테이블 이름
public class Orderr {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    private Long OrderNumber;   //주문 번호

    @Column(nullable = false)   //null 값 금지
    private Integer OrderQuantity; //주문 수량

    @ManyToOne  //단방향
    @JoinColumn(name = "GuestNumber") //fk
    private Guest guest;              //고객 번호

    @ManyToOne  //단방향
    @JoinColumn(name = "MenuNumber") //fk
    private Menu menu;              // 메뉴 번호


}
