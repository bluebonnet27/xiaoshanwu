package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author makejava
 * @since 2022-03-05 13:27:51
 */
public class Article implements Serializable {
    private static final long serialVersionUID = 390236131988862301L;
    
    private Integer articleid;
    
    private Integer articlethemeid;
    
    private Integer articleauthorid;
    
    private Integer articlereplycount;
    
    private String articletitle;
    
    private String articlecontent;
    
    private Date articlepublishtime;
    
    private Date articlechangetime;
    
    private Integer articlethumb;
    
    private Integer articlecollect;
    
    private Integer articlehot;


    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getArticlethemeid() {
        return articlethemeid;
    }

    public void setArticlethemeid(Integer articlethemeid) {
        this.articlethemeid = articlethemeid;
    }

    public Integer getArticleauthorid() {
        return articleauthorid;
    }

    public void setArticleauthorid(Integer articleauthorid) {
        this.articleauthorid = articleauthorid;
    }

    public Integer getArticlereplycount() {
        return articlereplycount;
    }

    public void setArticlereplycount(Integer articlereplycount) {
        this.articlereplycount = articlereplycount;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticlecontent() {
        return articlecontent;
    }

    public void setArticlecontent(String articlecontent) {
        this.articlecontent = articlecontent;
    }

    public Date getArticlepublishtime() {
        return articlepublishtime;
    }

    public void setArticlepublishtime(Date articlepublishtime) {
        this.articlepublishtime = articlepublishtime;
    }

    public Date getArticlechangetime() {
        return articlechangetime;
    }

    public void setArticlechangetime(Date articlechangetime) {
        this.articlechangetime = articlechangetime;
    }

    public Integer getArticlethumb() {
        return articlethumb;
    }

    public void setArticlethumb(Integer articlethumb) {
        this.articlethumb = articlethumb;
    }

    public Integer getArticlecollect() {
        return articlecollect;
    }

    public void setArticlecollect(Integer articlecollect) {
        this.articlecollect = articlecollect;
    }

    public Integer getArticlehot() {
        return articlehot;
    }

    public void setArticlehot(Integer articlehot) {
        this.articlehot = articlehot;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleid=" + articleid +
                ", articlethemeid=" + articlethemeid +
                ", articleauthorid=" + articleauthorid +
                ", articlereplycount=" + articlereplycount +
                ", articletitle='" + articletitle + '\'' +
                ", articlecontent='" + articlecontent + '\'' +
                ", articlepublishtime=" + articlepublishtime +
                ", articlechangetime=" + articlechangetime +
                ", articlethumb=" + articlethumb +
                ", articlecollect=" + articlecollect +
                ", articlehot=" + articlehot +
                '}';
    }
}

