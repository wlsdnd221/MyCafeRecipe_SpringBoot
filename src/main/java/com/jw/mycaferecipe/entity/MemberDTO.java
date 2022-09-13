package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
