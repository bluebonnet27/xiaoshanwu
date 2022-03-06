package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.dao.ArticleDao;
import com.ti.xiaoshanwu.entity.impl.ArticleImpl;
import com.ti.xiaoshanwu.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2022-03-05 13:27:51
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param articleid 主键
     * @return 实例对象
     */
    @Override
    public Article queryById(Integer articleid) {
        return this.articleDao.queryById(articleid);
    }

    /**
     * 分页查询
     *
     * @param article 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Article> queryByPage(Article article,PageRequest pageRequest) {
        long total = this.articleDao.count(article);
        return new PageImpl<>(this.articleDao.queryAllByLimit(article, pageRequest), pageRequest, total);
    }

    @Override
    public Page<Article> queryByPage1(Article article , PageRequest pageRequest) {
        long total = this.articleDao.count1(article);
        return new PageImpl<>(this.articleDao.queryAllByLimit1(article , pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article insert(Article article) {
        this.articleDao.insert(article);
        return article;
    }

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article update(Article article) {
        this.articleDao.update(article);
        return this.queryById(article.getArticleid());
    }

    /**
     * 通过主键删除数据
     *
     * @param articleid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer articleid) {
        return this.articleDao.deleteById(articleid) > 0;
    }

    /**
     * Convert to article array list.
     *
     * @param articles the articles
     * @return the array list
     */
    @Override
    public ArrayList<ArticleImpl> convertToArticleImpl(ArrayList<Article> articles) {
        return null;
    }
}
