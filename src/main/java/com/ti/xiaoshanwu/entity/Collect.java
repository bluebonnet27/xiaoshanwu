package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Collect)实体类
 *
 * @author makejava
 * @since 2022-03-22 22:11:22
 */
public class Collect implements Serializable {
    private static final long serialVersionUID = -58935127664895770L;
    
    private Integer collectid;
    
    private Integer userid;
    
    private Integer articleid;
    
    private Date collecttime;
    
    private Integer collecttype;


    public Integer getCollectid() {
        return collectid;
    }

    public void setCollectid(Integer collectid) {
        this.collectid = collectid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }

    public Integer getCollecttype() {
        return collecttype;
    }

    public void setCollecttype(Integer collecttype) {
        this.collecttype = collecttype;
    }

}

