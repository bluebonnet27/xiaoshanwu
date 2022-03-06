package com.ti.xiaoshanwu.entity.impl;

import com.ti.xiaoshanwu.entity.Article;

/**
 * @author TiHongsheng
 */
public class ArticleImpl extends Article {

    private String articleauthoridImpl;

    private String articleauthorImg;

    public String getArticleauthoridImpl() {
        return articleauthoridImpl;
    }

    public String getArticleauthorImg() {
        return articleauthorImg;
    }

    public void setArticleauthoridImpl(String articleauthoridImpl) {
        this.articleauthoridImpl = articleauthoridImpl;
    }

    public void setArticleauthorImg(String articleauthorImg) {
        this.articleauthorImg = articleauthorImg;
    }

}
