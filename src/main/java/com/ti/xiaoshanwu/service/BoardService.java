package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Board;
import com.ti.xiaoshanwu.entity.impl.BoardImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Board)表服务接口
 *
 * @author makejava
 * @since 2022-03-29 09:48:57
 */
public interface BoardService {

    /**
     * 通过ID查询单条数据
     *
     * @param boardid 主键
     * @return 实例对象
     */
    Board queryById(Integer boardid);

    /**
     * 分页查询
     *
     * @param board 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Board> queryByPage(Board board, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param board 实例对象
     * @return 实例对象
     */
    Board insert(Board board);

    /**
     * 修改数据
     *
     * @param board 实例对象
     * @return 实例对象
     */
    Board update(Board board);

    /**
     * 通过主键删除数据
     *
     * @param boardid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer boardid);

    /**
     * 转换为impl扩展类.
     *
     * @param board 源board
     * @return 目标扩展类
     */
    BoardImpl convertToBoardImpl(Board board);

}
