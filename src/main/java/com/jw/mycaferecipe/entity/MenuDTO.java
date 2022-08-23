package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "menu")
public class MenuDTO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String product;
    private String sort;
    private String filename;
    private String filepath;
    private String content;
}
