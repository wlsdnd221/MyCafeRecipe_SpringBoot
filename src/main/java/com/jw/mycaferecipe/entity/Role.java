package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String name;

    @ManyToMany(mappedBy = "roleList")
    private List<MemberDTO> memberDTOList;
}
