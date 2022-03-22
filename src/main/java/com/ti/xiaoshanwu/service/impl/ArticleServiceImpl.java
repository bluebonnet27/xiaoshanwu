package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.dao.ThemeDao;
import com.ti.xiaoshanwu.dao.UserDao;
import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.dao.ArticleDao;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.ArticleImpl;
import com.ti.xiaoshanwu.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private UserDao userDao;

    @Resource
    private ThemeDao themeDao;

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

    @Override
    public Page<Article> queryByPageNew(Article article, PageRequest pageRequest) {
        long total = this.articleDao.count1(article);
        return new PageImpl<>(this.articleDao.queryAllByLimitNew(article , pageRequest), pageRequest, total);
    }

    @Override
    public List<Article> queryArticles(Article article) {
        return this.articleDao.queryAllByLimitNoPage(article);
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

    /**
     * Convert to article article.
     *
     * @param article the article
     * @return the article
     */
    @Override
    public ArticleImpl convertToArticleImpl(Article article) {
        ArticleImpl articleImpl = new ArticleImpl();
        copyProperties(article,articleImpl);
        HeadImgConverter headImgConverter = new HeadImgConverter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        User targetUser = this.userDao.queryById(article.getArticleauthorid());
        String articleAuthorNameImpl = targetUser.getUsername();
        String articleAuthorHeadImgUrlImpl =
                headImgConverter.imgConvert(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        String themeName = this.themeDao.queryById(article.getArticlethemeid()).getThemename();
        String bgimg =
                headImgConverter.imgConvertBg(targetUser.getUserimg()==null?0:targetUser.getUserimg());

        String publishDate = dateFormat.format(article.getArticlepublishtime());
        String changeDate = dateFormat.format(article.getArticlechangetime());

        articleImpl.setArticleauthoridImpl(articleAuthorNameImpl);
        articleImpl.setArticleauthorImg(articleAuthorHeadImgUrlImpl);
        articleImpl.setThemeName(themeName);
        articleImpl.setArticleImgUrl(bgimg);
        articleImpl.setArticlePublishTimeImpl(publishDate);
        articleImpl.setArticleChangeTimeImpl(changeDate);
        return articleImpl;
    }
}
