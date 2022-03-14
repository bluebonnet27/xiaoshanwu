package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Theme)实体类
 *
 * @author makejava
 * @since 2022-03-05 17:10:47
 */
public class Theme implements Serializable {
    private static final long serialVersionUID = 895409163182448244L;
    
    private Integer themeid;
    
    private String themename;
    
    private Integer themeadminid;
    
    private String themestatement;
    
    private Date themetime;
    
    private Long themecount;


    public Integer getThemeid() {
        return themeid;
    }

    public void setThemeid(Integer themeid) {
        this.themeid = themeid;
    }

    public String getThemename() {
        return themename;
    }

    public void setThemename(String themename) {
        this.themename = themename;
    }

    public Integer getThemeadminid() {
        return themeadminid;
    }

    public void setThemeadminid(Integer themeadminid) {
        this.themeadminid = themeadminid;
    }

    public String getThemestatement() {
        return themestatement;
    }

    public void setThemestatement(String themestatement) {
        this.themestatement = themestatement;
    }

    public Date getThemetime() {
        return themetime;
    }

    public void setThemetime(Date themetime) {
        this.themetime = themetime;
    }

    public Long getThemecount() {
        return themecount;
    }

    public void setThemecount(Long themecount) {
        this.themecount = themecount;
    }

    public Theme() {
    }
}

