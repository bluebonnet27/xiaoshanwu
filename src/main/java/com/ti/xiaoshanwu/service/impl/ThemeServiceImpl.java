package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.dao.UserDao;
import com.ti.xiaoshanwu.entity.Theme;
import com.ti.xiaoshanwu.dao.ThemeDao;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.ThemeImpl;
import com.ti.xiaoshanwu.service.ThemeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

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

    @Resource
    private UserDao userDao;

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
     * 不分页，直接查询所有主题
     *
     * @param theme 筛选条件
     * @return 查询结果
     */
    @Override
    public List<Theme> queryByNoPage(Theme theme) {
        return this.themeDao.queryAllByLimitNoPage(theme);
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

    @Override
    public ThemeImpl convertToThemeImpl(Theme theme) {
        ThemeImpl themeImpl = new ThemeImpl();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

        String newDate = dateFormat.format(theme.getThemetime());
        User foundUser = this.userDao.queryById(theme.getThemeadminid());

        copyProperties(theme,themeImpl);
        themeImpl.setThemeDateImpl(newDate);
        themeImpl.setThemeadminidImpl(foundUser.getUsername());

        String imgHead;
        String imgBg;
        switch (theme.getThemeid()){
            case 0:
                imgHead = "https://s1.328888.xyz/2022/03/22/9ckMC.jpg";
                imgBg = "https://s1.328888.xyz/2022/03/22/9c1fg.jpg";
                break;
            case 1:
                imgHead = "https://s1.328888.xyz/2022/03/22/9ckMC.jpg";
                imgBg = "https://s3.bmp.ovh/imgs/2022/03/70523eeb599b7e79.png";
                break;
            case 2:
                imgHead = "https://s3.bmp.ovh/imgs/2022/03/33f5b3344697a6b6.jpg";
                imgBg = "https://s3.bmp.ovh/imgs/2022/03/aa62186f5c4c3fec.png";
                break;
            case 3:
                imgHead = "https://s3.bmp.ovh/imgs/2022/03/bb97814045765fb8.jpg";
                imgBg = "https://s3.bmp.ovh/imgs/2022/03/a7212ebdedb69e6f.png";
                break;
            case 4:
                imgHead = "https://s3.bmp.ovh/imgs/2022/03/ff85c7c2a10bb57f.jpg";
                imgBg = "https://s3.bmp.ovh/imgs/2022/03/2b96064d0e5c335f.jpg";
                break;
            default:
                imgHead = "https://s1.328888.xyz/2022/03/22/9ckMC.jpg";
                imgBg = "https://s1.328888.xyz/2022/03/22/9c1fg.jpg";
                break;
        }

        themeImpl.setThemeHeadImgImpl(imgHead);
        themeImpl.setThemeBgImgImpl(imgBg);

        return themeImpl;
    }
}
