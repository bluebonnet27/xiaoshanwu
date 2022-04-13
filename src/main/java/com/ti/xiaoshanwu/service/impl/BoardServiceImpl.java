package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.dao.ThemeDao;
import com.ti.xiaoshanwu.entity.Board;
import com.ti.xiaoshanwu.dao.BoardDao;
import com.ti.xiaoshanwu.entity.Theme;
import com.ti.xiaoshanwu.entity.impl.BoardImpl;
import com.ti.xiaoshanwu.service.BoardService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * (Board)表服务实现类
 *
 * @author makejava
 * @since 2022-03-29 09:48:57
 */
@Service("boardService")
public class BoardServiceImpl implements BoardService {
    @Resource
    private BoardDao boardDao;

    @Resource
    private ThemeDao themeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param boardid 主键
     * @return 实例对象
     */
    @Override
    public Board queryById(Integer boardid) {
        return this.boardDao.queryById(boardid);
    }

    /**
     * 分页查询
     *
     * @param board 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Board> queryByPage(Board board, PageRequest pageRequest) {
        long total = this.boardDao.count(board);
        return new PageImpl<>(this.boardDao.queryAllByLimit(board, pageRequest), pageRequest, total);
    }

    /**
     * 不分页查询所有数据.
     *
     * @param board the board
     * @return the list
     */
    @Override
    public List<Board> queryByNoPage(Board board) {
        return this.boardDao.queryAllByLimitNoPage(board);
    }

    /**
     * 新增数据
     *
     * @param board 实例对象
     * @return 实例对象
     */
    @Override
    public Board insert(Board board) {
        this.boardDao.insert(board);
        return board;
    }

    /**
     * 修改数据
     *
     * @param board 实例对象
     * @return 实例对象
     */
    @Override
    public Board update(Board board) {
        this.boardDao.update(board);
        return this.queryById(board.getBoardid());
    }

    /**
     * 通过主键删除数据
     *
     * @param boardid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer boardid) {
        return this.boardDao.deleteById(boardid) > 0;
    }

    /**
     * 转换为impl扩展类.
     *
     * @param board 源board
     * @return 目标扩展类
     */
    @Override
    public BoardImpl convertToBoardImpl(Board board) {
        BoardImpl newBoardImpl = new BoardImpl();
        copyProperties(board,newBoardImpl);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        String themeName;
        if(board.getBoardtheme()!=-1){
            Theme themeForBoard = this.themeDao.queryById(board.getBoardtheme());
            themeName = themeForBoard.getThemename();
        }else {
            themeName = "全局公告";
        }

        newBoardImpl.setBoardDateStringImpl(dateFormat.format(board.getBoarddate()));
        newBoardImpl.setBoardThemeNameImpl(themeName);

        return newBoardImpl;
    }
}
