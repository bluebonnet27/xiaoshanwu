package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Comment;
import com.ti.xiaoshanwu.entity.impl.CommentImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2022-03-24 11:45:37
 */
public interface CommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param commentid 主键
     * @return 实例对象
     */
    Comment queryById(Integer commentid);

    /**
     * 分页查询
     *
     * @param comment 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Comment> queryByPage(Comment comment, PageRequest pageRequest);


    /**
     * 分页查询，指定顺序
     *
     * @param comment     the comment
     * @param pageRequest the page request
     * @param order       the order
     * @return the page
     */
    Page<Comment> queryByPage(Comment comment,PageRequest pageRequest,Integer order);

    /**
     * 不分页查询.
     *
     * @param comment the comment
     * @return the list
     */
    List<Comment> queryByPage(Comment comment);

    /**
     * 获取最新一条评论.
     *
     * @param comment the comment
     * @return the newest comment
     */
    Comment getNewestComment(Comment comment);

    /**
     * 查询满足要求的帖子数量.
     *
     * @param comment the comment
     * @return the long
     */
    long countComment(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param commentid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer commentid);

    CommentImpl convertToCommentImpl(Comment comment);
}
