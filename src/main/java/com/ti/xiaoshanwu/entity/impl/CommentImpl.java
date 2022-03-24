package com.ti.xiaoshanwu.entity.impl;

import com.ti.xiaoshanwu.entity.Comment;

public class CommentImpl extends Comment {
    private String commentUserNameImpl;

    private String commentUserHeadImpl;

    private String commentDateImpl;

    public String getCommentUserNameImpl() {
        return commentUserNameImpl;
    }

    public void setCommentUserNameImpl(String commentUserNameImpl) {
        this.commentUserNameImpl = commentUserNameImpl;
    }

    public String getCommentUserHeadImpl() {
        return commentUserHeadImpl;
    }

    public void setCommentUserHeadImpl(String commentUserHeadImpl) {
        this.commentUserHeadImpl = commentUserHeadImpl;
    }

    public String getCommentDateImpl() {
        return commentDateImpl;
    }

    public void setCommentDateImpl(String commentDateImpl) {
        this.commentDateImpl = commentDateImpl;
    }
}
