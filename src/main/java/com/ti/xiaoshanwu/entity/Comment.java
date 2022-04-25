package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2022-03-24 11:45:37
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 907907067702043805L;
    
    private Integer commentid;
    
    private Integer commentarticleid;
    
    private Integer commentuserid;
    
    private String commentcontent;
    
    private Date commenttime;
    
    private Integer commentthumb;
    
    private Integer commenthot;
    
    private Integer commenttype;


    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getCommentarticleid() {
        return commentarticleid;
    }

    public void setCommentarticleid(Integer commentarticleid) {
        this.commentarticleid = commentarticleid;
    }

    public Integer getCommentuserid() {
        return commentuserid;
    }

    public void setCommentuserid(Integer commentuserid) {
        this.commentuserid = commentuserid;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public Integer getCommentthumb() {
        return commentthumb;
    }

    public void setCommentthumb(Integer commentthumb) {
        this.commentthumb = commentthumb;
    }

    public Integer getCommenthot() {
        return commenthot;
    }

    public void setCommenthot(Integer commenthot) {
        this.commenthot = commenthot;
    }

    public Integer getCommenttype() {
        return commenttype;
    }

    public void setCommenttype(Integer commenttype) {
        this.commenttype = commenttype;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentid=" + commentid +
                ", commentarticleid=" + commentarticleid +
                ", commentuserid=" + commentuserid +
                ", commentcontent='" + commentcontent + '\'' +
                ", commenttime=" + commenttime +
                ", commentthumb=" + commentthumb +
                ", commenthot=" + commenthot +
                ", commenttype=" + commenttype +
                '}';
    }
}

