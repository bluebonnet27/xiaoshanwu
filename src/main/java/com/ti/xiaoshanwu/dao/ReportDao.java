package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Report)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-31 20:26:57
 */
@Mapper
public interface ReportDao {

    /**
     * 通过ID查询单条数据
     *
     * @param reportid 主键
     * @return 实例对象
     */
    Report queryById(Integer reportid);

    /**
     * 查询指定行数据
     *
     * @param report 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Report> queryAllByLimit(Report report, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param report 查询条件
     * @return 总行数
     */
    long count(Report report);

    /**
     * 新增数据
     *
     * @param report 实例对象
     * @return 影响行数
     */
    int insert(Report report);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Report> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Report> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Report> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Report> entities);

    /**
     * 修改数据
     *
     * @param report 实例对象
     * @return 影响行数
     */
    int update(Report report);

    /**
     * 通过主键删除数据
     *
     * @param reportid 主键
     * @return 影响行数
     */
    int deleteById(Integer reportid);

}

