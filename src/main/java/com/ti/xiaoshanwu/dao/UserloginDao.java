package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Userlogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Userlogin)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-22 12:24:10
 */
@Mapper
public interface UserloginDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    Userlogin queryById(String userid);

    /**
     * 查询指定行数据
     *
     * @param userlogin 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Userlogin> queryAllByLimit(Userlogin userlogin, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userlogin 查询条件
     * @return 总行数
     */
    long count(Userlogin userlogin);

    /**
     * 新增数据
     *
     * @param userlogin 实例对象
     * @return 影响行数
     */
    int insert(Userlogin userlogin);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Userlogin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Userlogin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Userlogin> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Userlogin> entities);

    /**
     * 修改数据
     *
     * @param userlogin 实例对象
     * @return 影响行数
     */
    int update(Userlogin userlogin);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(String userid);

}

