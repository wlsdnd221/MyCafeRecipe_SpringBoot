package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
CREATE TABLE IF NOT EXISTS `member` (
  `num` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  `id` varchar(50) DEFAULT '0',
  `pw` varchar(100) DEFAULT '0',
  `address` varchar(50) DEFAULT '0',
  `phone` varchar(50) DEFAULT '0',
  `email` varchar(50) DEFAULT '0',
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`num`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `member_role` (
  `member_num` bigint(20) NOT NULL,
  `role_num` bigint(20) NOT NULL,
  PRIMARY KEY (`member_num`,`role_num`) USING BTREE,
  KEY `FK__role` (`role_num`) USING BTREE,
  CONSTRAINT `FK_member_role_member` FOREIGN KEY (`member_num`) REFERENCES `member` (`num`),
  CONSTRAINT `FK_member_role_role` FOREIGN KEY (`role_num`) REFERENCES `role` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `role` (`num`, `name`) VALUES
	(1, 'ROLE_USER');
*/
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
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "member_role"
              ,joinColumns = @JoinColumn(name = "member_num")
              ,inverseJoinColumns = @JoinColumn(name = "role_num"))
    private List<Role> roleList = new ArrayList<>();
}
