package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;

/*
CREATE TABLE IF NOT EXISTS `menu` (
  `num` bigint(20) NOT NULL AUTO_INCREMENT,
  `product` varchar(50) DEFAULT '0',
  `sort` varchar(50) DEFAULT '0',
  `filename` varchar(1000) DEFAULT '0',
  `filepath` varchar(1000) DEFAULT '0',
  `content` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
 */

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
