package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/*
CREATE TABLE IF NOT EXISTS `role` (
  `num` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
 */

@Entity
@Data
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String name;

    @ManyToMany(mappedBy = "roleList")
    private List<MemberDTO> memberDTOList;
}
