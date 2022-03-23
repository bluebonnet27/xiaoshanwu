package com.ti.xiaoshanwu.entity.impl;

import com.ti.xiaoshanwu.entity.Collect;

/**
 * @author TiHongsheng
 */
public class CollectImpl extends Collect {
    private String userNameImpl;

    private String articleNameImpl;

    private String collectTimeImpl;

    private String collectThemeImpl;

    public String getUserNameImpl() {
        return userNameImpl;
    }

    public void setUserNameImpl(String userNameImpl) {
        this.userNameImpl = userNameImpl;
    }

    public String getArticleNameImpl() {
        return articleNameImpl;
    }

    public void setArticleNameImpl(String articleNameImpl) {
        this.articleNameImpl = articleNameImpl;
    }

    public String getCollectTimeImpl() {
        return collectTimeImpl;
    }

    public void setCollectTimeImpl(String collectTimeImpl) {
        this.collectTimeImpl = collectTimeImpl;
    }

    public String getCollectThemeImpl() {
        return collectThemeImpl;
    }

    public void setCollectThemeImpl(String collectThemeImpl) {
        this.collectThemeImpl = collectThemeImpl;
    }

    public CollectImpl() {
    }
}
