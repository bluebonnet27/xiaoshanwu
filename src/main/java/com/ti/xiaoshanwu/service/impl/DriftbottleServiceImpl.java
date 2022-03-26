package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.dao.UserDao;
import com.ti.xiaoshanwu.entity.Driftbottle;
import com.ti.xiaoshanwu.dao.DriftbottleDao;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.DriftbottleImpl;
import com.ti.xiaoshanwu.service.DriftbottleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * (Driftbottle)表服务实现类
 *
 * @author makejava
 * @since 2022-03-26 10:13:48
 */
@Service("driftbottleService")
public class DriftbottleServiceImpl implements DriftbottleService {
    @Resource
    private DriftbottleDao driftbottleDao;

    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param bottleid 主键
     * @return 实例对象
     */
    @Override
    public Driftbottle queryById(Integer bottleid) {
        return this.driftbottleDao.queryById(bottleid);
    }

    /**
     * Query random driftbottle.
     *
     * @param recordNum the record num
     * @return the driftbottle
     */
    @Override
    public Driftbottle queryRandom(Integer recordNum) {
        return this.driftbottleDao.randomQuery(recordNum);
    }

    /**
     * 分页查询
     *
     * @param driftbottle 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Driftbottle> queryByPage(Driftbottle driftbottle, PageRequest pageRequest) {
        long total = this.driftbottleDao.count(driftbottle);
        return new PageImpl<>(this.driftbottleDao.queryAllByLimit(driftbottle, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param driftbottle 实例对象
     * @return 实例对象
     */
    @Override
    public Driftbottle insert(Driftbottle driftbottle) {
        this.driftbottleDao.insert(driftbottle);
        return driftbottle;
    }

    /**
     * 修改数据
     *
     * @param driftbottle 实例对象
     * @return 实例对象
     */
    @Override
    public Driftbottle update(Driftbottle driftbottle) {
        this.driftbottleDao.update(driftbottle);
        return this.queryById(driftbottle.getBottleid());
    }

    /**
     * 通过主键删除数据
     *
     * @param bottleid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer bottleid) {
        return this.driftbottleDao.deleteById(bottleid) > 0;
    }

    /**
     * driftbottle转换为driftbottleimpl
     *
     * @param driftbottle the driftbottle
     * @return the driftbottle
     */
    @Override
    public DriftbottleImpl convertToDriftbottleImpl(Driftbottle driftbottle) {
        DriftbottleImpl driftbottleImpl = new DriftbottleImpl();
        copyProperties(driftbottle,driftbottleImpl);

        HeadImgConverter headImgConverter = new HeadImgConverter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        User sendUser = this.userDao.queryById(driftbottle.getBottlesendid());
        driftbottleImpl.setBottlesendidNameImpl(sendUser.getUsername());

        String sendDate = dateFormat.format(driftbottle.getBottletime());
        driftbottleImpl.setBottletimeStringImpl(sendDate);

        String bgimg =
                headImgConverter.imgConvert(sendUser.getUserimg()==null?0:sendUser.getUserimg());
        driftbottleImpl.setBottlesendidImgImpl(bgimg);

        return driftbottleImpl;
    }
}
