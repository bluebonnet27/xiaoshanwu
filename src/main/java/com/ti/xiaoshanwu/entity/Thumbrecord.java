package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Thumbrecord)实体类
 *
 * @author makejava
 * @since 2022-03-23 00:35:02
 */
public class Thumbrecord implements Serializable {
    private static final long serialVersionUID = -58702128941202900L;
    
    private Integer thumbid;
    
    private Integer thumbby;
    
    private Integer thumbbytype;
    
    private Integer thumbto;
    
    private Integer thumbtotype;
    
    private Date thumbtime;


    public Integer getThumbid() {
        return thumbid;
    }

    public void setThumbid(Integer thumbid) {
        this.thumbid = thumbid;
    }

    public Integer getThumbby() {
        return thumbby;
    }

    public void setThumbby(Integer thumbby) {
        this.thumbby = thumbby;
    }

    public Integer getThumbbytype() {
        return thumbbytype;
    }

    public void setThumbbytype(Integer thumbbytype) {
        this.thumbbytype = thumbbytype;
    }

    public Integer getThumbto() {
        return thumbto;
    }

    public void setThumbto(Integer thumbto) {
        this.thumbto = thumbto;
    }

    public Integer getThumbtotype() {
        return thumbtotype;
    }

    public void setThumbtotype(Integer thumbtotype) {
        this.thumbtotype = thumbtotype;
    }

    public Date getThumbtime() {
        return thumbtime;
    }

    public void setThumbtime(Date thumbtime) {
        this.thumbtime = thumbtime;
    }

    public Thumbrecord() {
    }
}

