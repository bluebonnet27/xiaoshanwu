package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Notice)实体类
 *
 * @author makejava
 * @since 2022-04-15 10:12:39
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = 417552066759320876L;
    
    private Integer noticeid;
    
    private Date noticetime;
    
    private Integer noticefromtype;
    
    private Integer noticefromid;
    
    private Integer noticetotype;
    
    private Integer noticetoid;
    
    private Integer noticetype;
    
    private String noticetitle;
    
    private String noticecontent;
    
    private Integer noticestate;


    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    public Date getNoticetime() {
        return noticetime;
    }

    public void setNoticetime(Date noticetime) {
        this.noticetime = noticetime;
    }

    public Integer getNoticefromtype() {
        return noticefromtype;
    }

    public void setNoticefromtype(Integer noticefromtype) {
        this.noticefromtype = noticefromtype;
    }

    public Integer getNoticefromid() {
        return noticefromid;
    }

    public void setNoticefromid(Integer noticefromid) {
        this.noticefromid = noticefromid;
    }

    public Integer getNoticetotype() {
        return noticetotype;
    }

    public void setNoticetotype(Integer noticetotype) {
        this.noticetotype = noticetotype;
    }

    public Integer getNoticetoid() {
        return noticetoid;
    }

    public void setNoticetoid(Integer noticetoid) {
        this.noticetoid = noticetoid;
    }

    public Integer getNoticetype() {
        return noticetype;
    }

    public void setNoticetype(Integer noticetype) {
        this.noticetype = noticetype;
    }

    public String getNoticetitle() {
        return noticetitle;
    }

    public void setNoticetitle(String noticetitle) {
        this.noticetitle = noticetitle;
    }

    public String getNoticecontent() {
        return noticecontent;
    }

    public void setNoticecontent(String noticecontent) {
        this.noticecontent = noticecontent;
    }

    public Integer getNoticestate() {
        return noticestate;
    }

    public void setNoticestate(Integer noticestate) {
        this.noticestate = noticestate;
    }

}

