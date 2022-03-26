package com.ti.xiaoshanwu.service;

import com.ti.xiaoshanwu.entity.Theme;
import com.ti.xiaoshanwu.entity.impl.ThemeImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Theme)表服务接口
 *
 * @author makejava
 * @since 2022-03-05 17:10:47
 */
public interface ThemeService {

    /**
     * 通过ID查询单条数据
     *
     * @param themeid 主键
     * @return 实例对象
     */
    Theme queryById(Integer themeid);

    /**
     * 分页查询
     *
     * @param theme 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Theme> queryByPage(Theme theme, PageRequest pageRequest);

    /**
     * 不分页，直接查询所有主题
     *
     * @param theme 筛选条件
     * @return 查询结果
     */
    List<Theme> queryByNoPage(Theme theme);

    /**
     * 新增数据
     *
     * @param theme 实例对象
     * @return 实例对象
     */
    Theme insert(Theme theme);

    /**
     * 修改数据
     *
     * @param theme 实例对象
     * @return 实例对象
     */
    Theme update(Theme theme);

    /**
     * 通过主键删除数据
     *
     * @param themeid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer themeid);

    ThemeImpl convertToThemeImpl(Theme theme);

}
