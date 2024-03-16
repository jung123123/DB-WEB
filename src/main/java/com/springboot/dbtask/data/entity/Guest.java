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
@Table(name = "guest")  //테이블 이름
public class Guest {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    private Long GuestNumber;   //고객 번호

    @Column(columnDefinition = "VARCHAR(50)")   //null 값 가능, 최대 길이 50
    private String GuestName;   //고객 이름

}
