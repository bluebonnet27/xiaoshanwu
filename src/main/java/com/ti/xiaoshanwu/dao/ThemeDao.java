package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Theme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Theme)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-05 17:10:47
 */
@Mapper
public interface ThemeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param themeid 主键
     * @return 实例对象
     */
    Theme queryById(Integer themeid);

    /**
     * 查询指定行数据
     *
     * @param theme 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Theme> queryAllByLimit(@Param("tm") Theme theme, @Param("pageable") Pageable pageable);

    /**
     * 查询指定行数据，不分页
     *
     * @param theme 查询条件
     * @return 对象列表
     */
    List<Theme> queryAllByLimitNoPage(@Param("tm") Theme theme);

    /**
     * 统计总行数
     *
     * @param theme 查询条件
     * @return 总行数
     */
    long count(Theme theme);

    /**
     * 新增数据
     *
     * @param theme 实例对象
     * @return 影响行数
     */
    int insert(Theme theme);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Theme> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Theme> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Theme> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Theme> entities);

    /**
     * 修改数据
     *
     * @param theme 实例对象
     * @return 影响行数
     */
    int update(Theme theme);

    /**
     * 通过主键删除数据
     *
     * @param themeid 主键
     * @return 影响行数
     */
    int deleteById(Integer themeid);

}

