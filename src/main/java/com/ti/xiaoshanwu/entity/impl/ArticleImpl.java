package com.ti.xiaoshanwu.entity.impl;

import com.ti.xiaoshanwu.entity.Article;

/**
 * @author TiHongsheng
 */
public class ArticleImpl extends Article {

    private String articleauthoridImpl;

    private String articleauthorImg;

    private String themeName;

    private String themeNameColor;

    private String articleImgUrl;

    private String articlePublishTimeImpl;

    private String articleChangeTimeImpl;

    public String getArticleauthoridImpl() {
        return articleauthoridImpl;
    }

    public String getArticleauthorImg() {
        return articleauthorImg;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeNameColor() {
        return themeNameColor;
    }

    public void setThemeNameColor(String themeNameColor) {
        this.themeNameColor = themeNameColor;
    }

    public String getArticleImgUrl() {
        return articleImgUrl;
    }

    public void setArticleImgUrl(String articleImgUrl) {
        this.articleImgUrl = articleImgUrl;
    }

    public void setArticleauthoridImpl(String articleauthoridImpl) {
        this.articleauthoridImpl = articleauthoridImpl;
    }

    public void setArticleauthorImg(String articleauthorImg) {
        this.articleauthorImg = articleauthorImg;
    }

    public String getArticlePublishTimeImpl() {
        return articlePublishTimeImpl;
    }

    public void setArticlePublishTimeImpl(String articlePublishTimeImpl) {
        this.articlePublishTimeImpl = articlePublishTimeImpl;
    }

    public String getArticleChangeTimeImpl() {
        return articleChangeTimeImpl;
    }

    public void setArticleChangeTimeImpl(String articleChangeTimeImpl) {
        this.articleChangeTimeImpl = articleChangeTimeImpl;
    }
}
