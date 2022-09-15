package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/*
CREATE TABLE IF NOT EXISTS `board` (
  `num` bigint(20) NOT NULL AUTO_INCREMENT,
  `writer` varchar(50) DEFAULT NULL,
  `writeday` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `readcnt` int(10) DEFAULT 0,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
 */

@Entity
@Data
@Table(name = "board")
public class BoardDTO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String writer;
    private String writeday;
    private int readcnt;
    private String title;
    private String content;


}
