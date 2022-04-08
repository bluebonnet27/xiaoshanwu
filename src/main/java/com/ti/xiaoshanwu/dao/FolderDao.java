package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Folder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Folder)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-08 10:23:14
 */
@Mapper
public interface FolderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param folderid 主键
     * @return 实例对象
     */
    Folder queryById(Integer folderid);

    /**
     * 查询指定行数据
     *
     * @param folder 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Folder> queryAllByLimit(Folder folder, @Param("pageable") Pageable pageable);

    /**
     * 查询指定行数据，不分页
     *
     * @param folder the folder
     * @return the list
     */
    List<Folder> queryAllByLimitNoPage(Folder folder);

    /**
     * 统计总行数
     *
     * @param folder 查询条件
     * @return 总行数
     */
    long count(Folder folder);

    /**
     * 新增数据
     *
     * @param folder 实例对象
     * @return 影响行数
     */
    int insert(Folder folder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Folder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Folder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Folder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Folder> entities);

    /**
     * 修改数据
     *
     * @param folder 实例对象
     * @return 影响行数
     */
    int update(Folder folder);

    /**
     * 通过主键删除数据
     *
     * @param folderid 主键
     * @return 影响行数
     */
    int deleteById(Integer folderid);

}

