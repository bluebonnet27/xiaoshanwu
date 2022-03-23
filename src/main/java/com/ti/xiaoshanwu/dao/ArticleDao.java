package com.ti.xiaoshanwu.dao;

import com.ti.xiaoshanwu.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-05 13:27:51
 */
@Mapper
public interface ArticleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param articleid 主键
     * @return 实例对象
     */
    Article queryById(Integer articleid);

    List<Article> queryAllByLimit(Article article, @Param("pageable") Pageable pageable);

    /**
     * 改进的分页查询
     *
     * @param article  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Article> queryAllByLimit1(@Param("ar") Article article, @Param("pageable") Pageable pageable);

    List<Article> queryAllByLimit2();

    List<Article> queryAllByLimitNew(@Param("ar") Article article, @Param("pageable") Pageable pageable);

    List<Article> queryAllByLimitNoPage(@Param("ar") Article article);

    /**
     * 改进的分页查询（热度）
     *
     * @param article  the article
     * @param pageable the pageable
     * @return the list
     */
    List<Article> queryAllByLimitHot(@Param("ar") Article article, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param article 查询条件
     * @return 总行数
     */
    long count(Article article);

    long count1(@Param("ar") Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int insert(Article article);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Article> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Article> entities);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int update(Article article);

    /**
     * 通过主键删除数据
     *
     * @param articleid 主键
     * @return 影响行数
     */
    int deleteById(Integer articleid);

}

