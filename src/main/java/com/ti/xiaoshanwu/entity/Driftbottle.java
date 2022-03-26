package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Driftbottle)实体类
 *
 * @author makejava
 * @since 2022-03-26 10:13:48
 */
public class Driftbottle implements Serializable {
    private static final long serialVersionUID = 195352956660150661L;
    
    private Integer bottleid;
    
    private Integer bottlesendid;
    
    private Date bottletime;
    
    private String bottlecontent;
    
    private Integer bottleacceptid;
    
    private Integer bottlestate;


    public Integer getBottleid() {
        return bottleid;
    }

    public void setBottleid(Integer bottleid) {
        this.bottleid = bottleid;
    }

    public Integer getBottlesendid() {
        return bottlesendid;
    }

    public void setBottlesendid(Integer bottlesendid) {
        this.bottlesendid = bottlesendid;
    }

    public Date getBottletime() {
        return bottletime;
    }

    public void setBottletime(Date bottletime) {
        this.bottletime = bottletime;
    }

    public String getBottlecontent() {
        return bottlecontent;
    }

    public void setBottlecontent(String bottlecontent) {
        this.bottlecontent = bottlecontent;
    }

    public Integer getBottleacceptid() {
        return bottleacceptid;
    }

    public void setBottleacceptid(Integer bottleacceptid) {
        this.bottleacceptid = bottleacceptid;
    }

    public Integer getBottlestate() {
        return bottlestate;
    }

    public void setBottlestate(Integer bottlestate) {
        this.bottlestate = bottlestate;
    }

}

