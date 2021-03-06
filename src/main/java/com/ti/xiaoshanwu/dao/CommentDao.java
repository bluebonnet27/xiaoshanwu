package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-24 11:45:37
 */
@Mapper
public interface CommentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param commentid 主键
     * @return 实例对象
     */
    Comment queryById(Integer commentid);

    /**
     * 查询指定行数据
     *
     * @param comment 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(@Param("co") Comment comment, @Param("pageable") Pageable pageable);

    /**
     * 查询指定行数据，按时间倒序
     *
     * @param comment 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Comment> queryAllByLimitNew(@Param("co") Comment comment, @Param("pageable") Pageable pageable);

    /**
     * Query all by limit new no page list.
     *
     * @param comment the comment
     * @return the list
     */
    List<Comment> queryAllByLimitNewNoPage(@Param("co") Comment comment);

    /**
     * 查询指定行数据，按热度倒序
     *
     * @param comment 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Comment> queryAllByLimitHot(@Param("co") Comment comment, @Param("pageable") Pageable pageable);

    /**
     * 查询指定行数据，按点赞倒序
     *
     * @param comment 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Comment> queryAllByLimitThumb(@Param("co") Comment comment, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param comment 查询条件
     * @return 总行数
     */
    long count(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Comment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Comment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Comment> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Comment> entities);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param commentid 主键
     * @return 影响行数
     */
    int deleteById(Integer commentid);

}

