package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Driftbottle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Driftbottle)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-26 10:13:48
 */
@Mapper
public interface DriftbottleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param bottleid 主键
     * @return 实例对象
     */
    Driftbottle queryById(Integer bottleid);

    /**
     * 查询指定行数据
     *
     * @param driftbottle 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Driftbottle> queryAllByLimit(Driftbottle driftbottle, @Param("pageable") Pageable pageable);

    /**
     * Random query driftbottle.
     *
     * @param recordnum the recordnum
     * @return the driftbottle
     */
    Driftbottle randomQuery(Integer recordnum);

    /**
     * 统计总行数
     *
     * @param driftbottle 查询条件
     * @return 总行数
     */
    long count(Driftbottle driftbottle);

    /**
     * 新增数据
     *
     * @param driftbottle 实例对象
     * @return 影响行数
     */
    int insert(Driftbottle driftbottle);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Driftbottle> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Driftbottle> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Driftbottle> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Driftbottle> entities);

    /**
     * 修改数据
     *
     * @param driftbottle 实例对象
     * @return 影响行数
     */
    int update(Driftbottle driftbottle);

    /**
     * 通过主键删除数据
     *
     * @param bottleid 主键
     * @return 影响行数
     */
    int deleteById(Integer bottleid);

}

