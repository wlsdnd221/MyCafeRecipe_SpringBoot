package com.jw.mycaferecipe.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "board")
public class BoardDTO {

    private Long num;
    private String writer;
    private String writeday;
    private String title;
    private List<MenuDTO> recipe;
    private String filename;
    private String filepath;
    private String content;


}
