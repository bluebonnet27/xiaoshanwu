package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Board)实体类
 *
 * @author makejava
 * @since 2022-03-29 09:48:57
 */
public class Board implements Serializable {
    private static final long serialVersionUID = 896995190077983732L;
    
    private Integer boardid;
    
    private Integer boardtype;
    
    private Date boarddate;
    
    private String boardtitle;
    
    private String boardcontent;
    
    private Integer boardthumb;
    
    private Integer boardagainst;
    
    private Integer boardtheme;


    public Integer getBoardid() {
        return boardid;
    }

    public void setBoardid(Integer boardid) {
        this.boardid = boardid;
    }

    public Integer getBoardtype() {
        return boardtype;
    }

    public void setBoardtype(Integer boardtype) {
        this.boardtype = boardtype;
    }

    public Date getBoarddate() {
        return boarddate;
    }

    public void setBoarddate(Date boarddate) {
        this.boarddate = boarddate;
    }

    public String getBoardtitle() {
        return boardtitle;
    }

    public void setBoardtitle(String boardtitle) {
        this.boardtitle = boardtitle;
    }

    public String getBoardcontent() {
        return boardcontent;
    }

    public void setBoardcontent(String boardcontent) {
        this.boardcontent = boardcontent;
    }

    public Integer getBoardthumb() {
        return boardthumb;
    }

    public void setBoardthumb(Integer boardthumb) {
        this.boardthumb = boardthumb;
    }

    public Integer getBoardagainst() {
        return boardagainst;
    }

    public void setBoardagainst(Integer boardagainst) {
        this.boardagainst = boardagainst;
    }

    public Integer getBoardtheme() {
        return boardtheme;
    }

    public void setBoardtheme(Integer boardtheme) {
        this.boardtheme = boardtheme;
    }

}

