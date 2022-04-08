package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.dao.*;
import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.entity.Collect;
import com.ti.xiaoshanwu.entity.Theme;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.CollectImpl;
import com.ti.xiaoshanwu.service.CollectService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * (Collect)表服务实现类
 *
 * @author makejava
 * @since 2022-03-22 22:11:22
 */
@Service("collectService")
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectDao collectDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ThemeDao themeDao;

    @Resource
    private FolderDao folderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param collectid 主键
     * @return 实例对象
     */
    @Override
    public Collect queryById(Integer collectid) {
        return this.collectDao.queryById(collectid);
    }

    /**
     * 分页查询
     *
     * @param collect 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Collect> queryByPage(Collect collect, PageRequest pageRequest) {
        long total = this.collectDao.count(collect);
        return new PageImpl<>(this.collectDao.queryAllByLimit(collect, pageRequest), pageRequest, total);
    }

    /**
     * 不分页查询
     *
     * @param collect 筛选条件
     * @return 查询结果
     */
    @Override
    public List<Collect> queryByNoPage(Collect collect) {
        return this.collectDao.queryAllByLimitNoPage(collect);
    }

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    @Override
    public Collect insert(Collect collect) {
        this.collectDao.insert(collect);
        return collect;
    }

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    @Override
    public Collect update(Collect collect) {
        this.collectDao.update(collect);
        return this.queryById(collect.getCollectid());
    }

    /**
     * 通过主键删除数据
     *
     * @param collectid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer collectid) {
        return this.collectDao.deleteById(collectid) > 0;
    }

    /**
     * Convert to collect collect.
     *
     * @param collect the collect
     * @return the collect
     */
    @Override
    public CollectImpl convertToCollectImpl(Collect collect) {
        CollectImpl collectImpl = new CollectImpl();
        copyProperties(collect,collectImpl);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        //用户
        User collectUser = this.userDao.queryById(collect.getUserid());
        collectImpl.setUserNameImpl(collectUser.getUsername());
        //文章
        Article article = this.articleDao.queryById(collect.getArticleid());
        collectImpl.setArticleNameImpl(article.getArticletitle());
        //主题
        Theme theme = this.themeDao.queryById(article.getArticlethemeid());
        collectImpl.setCollectThemeImpl(theme.getThemename());
        //收藏时间
        String collectTime = dateFormat.format(collect.getCollecttime());
        collectImpl.setCollectTimeImpl(collectTime);
        //收藏夹名称
        String collectFolderName = this.folderDao.queryById(collect.getCollecttype()).getFoldername();
        collectImpl.setCollectFolderNameImpl(collectFolderName);

        return collectImpl;
    }
}
