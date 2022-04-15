package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Notice)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-15 10:12:39
 */
@Mapper
public interface NoticeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param noticeid 主键
     * @return 实例对象
     */
    Notice queryById(Integer noticeid);

    /**
     * 查询指定行数据
     *
     * @param notice 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Notice> queryAllByLimit(@Param("no") Notice notice, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param notice 查询条件
     * @return 总行数
     */
    long count(Notice notice);

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 影响行数
     */
    int insert(Notice notice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Notice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Notice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Notice> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Notice> entities);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 影响行数
     */
    int update(Notice notice);

    /**
     * 通过主键删除数据
     *
     * @param noticeid 主键
     * @return 影响行数
     */
    int deleteById(Integer noticeid);

}

