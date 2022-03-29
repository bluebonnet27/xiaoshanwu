package com.ti.xiaoshanwu.entity.impl;

import com.ti.xiaoshanwu.entity.Board;

/**
 * The type Board.
 * @author TiHongsheng
 */
public class BoardImpl extends Board {
    private String boardThemeNameImpl;

    private String boardDateStringImpl;

    public String getBoardThemeNameImpl() {
        return boardThemeNameImpl;
    }

    public void setBoardThemeNameImpl(String boardThemeNameImpl) {
        this.boardThemeNameImpl = boardThemeNameImpl;
    }

    public String getBoardDateStringImpl() {
        return boardDateStringImpl;
    }

    public void setBoardDateStringImpl(String boardDateStringImpl) {
        this.boardDateStringImpl = boardDateStringImpl;
    }
}
