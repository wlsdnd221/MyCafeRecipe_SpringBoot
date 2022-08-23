package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "member")
public class MemberDTO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String name;
    private String id;
    private String pw;
    private String address;
    private String phone;
    private String email;

}
