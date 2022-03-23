package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Thumbrecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Thumbrecord)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-23 00:35:02
 */
@Mapper
public interface ThumbrecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param thumbid 主键
     * @return 实例对象
     */
    Thumbrecord queryById(Integer thumbid);

    /**
     * 查询指定行数据
     *
     * @param thumbrecord 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Thumbrecord> queryAllByLimit(Thumbrecord thumbrecord, @Param("pageable") Pageable pageable);

    List<Thumbrecord> queryAllBy4(Thumbrecord thumbrecord);

    /**
     * 统计总行数
     *
     * @param thumbrecord 查询条件
     * @return 总行数
     */
    long count(Thumbrecord thumbrecord);

    /**
     * 新增数据
     *
     * @param thumbrecord 实例对象
     * @return 影响行数
     */
    int insert(Thumbrecord thumbrecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Thumbrecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Thumbrecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Thumbrecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Thumbrecord> entities);

    /**
     * 修改数据
     *
     * @param thumbrecord 实例对象
     * @return 影响行数
     */
    int update(Thumbrecord thumbrecord);

    /**
     * 通过主键删除数据
     *
     * @param thumbid 主键
     * @return 影响行数
     */
    int deleteById(Integer thumbid);



}

