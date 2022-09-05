package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
