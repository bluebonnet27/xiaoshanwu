package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.entity.Theme;
import com.ti.xiaoshanwu.dao.ThemeDao;
import com.ti.xiaoshanwu.service.ThemeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Theme)表服务实现类
 *
 * @author makejava
 * @since 2022-03-05 17:10:47
 */
@Service("themeService")
public class ThemeServiceImpl implements ThemeService {
    @Resource
    private ThemeDao themeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param themeid 主键
     * @return 实例对象
     */
    @Override
    public Theme queryById(Integer themeid) {
        return this.themeDao.queryById(themeid);
    }

    /**
     * 分页查询
     *
     * @param theme 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Theme> queryByPage(Theme theme, PageRequest pageRequest) {
        long total = this.themeDao.count(theme);
        return new PageImpl<>(this.themeDao.queryAllByLimit(theme, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param theme 实例对象
     * @return 实例对象
     */
    @Override
    public Theme insert(Theme theme) {
        this.themeDao.insert(theme);
        return theme;
    }

    /**
     * 修改数据
     *
     * @param theme 实例对象
     * @return 实例对象
     */
    @Override
    public Theme update(Theme theme) {
        this.themeDao.update(theme);
        return this.queryById(theme.getThemeid());
    }

    /**
     * 通过主键删除数据
     *
     * @param themeid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer themeid) {
        return this.themeDao.deleteById(themeid) > 0;
    }
}
