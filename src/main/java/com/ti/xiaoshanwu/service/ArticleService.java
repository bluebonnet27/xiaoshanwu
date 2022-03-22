package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.entity.impl.ArticleImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2022-03-05 13:27:51
 */
public interface ArticleService {

    /**
     * 通过ID查询单条数据
     *
     * @param articleid 主键
     * @return 实例对象
     */
    Article queryById(Integer articleid);

    /**
     * 分页查询
     *
     * @param article 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Article> queryByPage(Article article, PageRequest pageRequest);

    Page<Article> queryByPage1(Article article,PageRequest pageRequest);

    Page<Article> queryByPageNew(Article article,PageRequest pageRequest);

    List<Article> queryArticles(Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    Article insert(Article article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    Article update(Article article);

    /**
     * 通过主键删除数据
     *
     * @param articleid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer articleid);

    /**
     * Convert to article array list.
     *
     * @param articles the articles
     * @return the array list
     */
    ArrayList<ArticleImpl> convertToArticleImpl(ArrayList<Article> articles);

    /**
     * Convert to article article.
     *
     * @param article the article
     * @return the article
     */
    ArticleImpl convertToArticleImpl(Article article);

}
