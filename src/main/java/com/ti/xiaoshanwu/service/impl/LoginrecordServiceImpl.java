package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.dao.AdminDao;
import com.ti.xiaoshanwu.dao.UserDao;
import com.ti.xiaoshanwu.entity.Admin;
import com.ti.xiaoshanwu.entity.Loginrecord;
import com.ti.xiaoshanwu.dao.LoginrecordDao;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.LoginrecordImpl;
import com.ti.xiaoshanwu.service.LoginrecordService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.text.SimpleDateFormat;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * (Loginrecord)表服务实现类
 *
 * @author makejava
 * @since 2022-04-06 10:28:44
 */
@Service("loginrecordService")
public class LoginrecordServiceImpl implements LoginrecordService {
    @Resource
    private LoginrecordDao loginrecordDao;

    @Resource
    private UserDao userDao;

    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param loginrecordid 主键
     * @return 实例对象
     */
    @Override
    public Loginrecord queryById(Integer loginrecordid) {
        return this.loginrecordDao.queryById(loginrecordid);
    }

    /**
     * 分页查询
     *
     * @param loginrecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Loginrecord> queryByPage(Loginrecord loginrecord, PageRequest pageRequest) {
        long total = this.loginrecordDao.count(loginrecord);
        return new PageImpl<>(this.loginrecordDao.queryAllByLimit(loginrecord, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param loginrecord 实例对象
     * @return 实例对象
     */
    @Override
    public Loginrecord insert(Loginrecord loginrecord) {
        this.loginrecordDao.insert(loginrecord);
        return loginrecord;
    }

    /**
     * 修改数据
     *
     * @param loginrecord 实例对象
     * @return 实例对象
     */
    @Override
    public Loginrecord update(Loginrecord loginrecord) {
        this.loginrecordDao.update(loginrecord);
        return this.queryById(loginrecord.getLoginrecordid());
    }

    /**
     * 通过主键删除数据
     *
     * @param loginrecordid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer loginrecordid) {
        return this.loginrecordDao.deleteById(loginrecordid) > 0;
    }

    /**
     * 转换为impl扩展.
     *
     * @param loginrecord the loginrecord
     * @return the loginrecord
     */
    @Override
    public LoginrecordImpl convertToLoginrecordImpl(Loginrecord loginrecord) {
        LoginrecordImpl newImpl = new LoginrecordImpl();

        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String loginTime = dateFormatTime.format(loginrecord.getLogintime());
        if(loginrecord.getLogintype() == 0){
            User loginUser = this.userDao.queryById(loginrecord.getLoginid());
            newImpl.setLogintypeImpl("用户");
            newImpl.setLoginidNameImpl(loginUser.getUsername());
        }else if(loginrecord.getLogintype() == 1){
            User loginUser = this.userDao.queryById(loginrecord.getLoginid());
            newImpl.setLogintypeImpl("主题管理员");
            newImpl.setLoginidNameImpl(loginUser.getUsername());
        }else {
            Admin loginAdmin = this.adminDao.queryById(loginrecord.getLoginid());
            newImpl.setLogintypeImpl("管理员");
            newImpl.setLoginidNameImpl("管理员："+loginAdmin.getAdminid());
        }

        copyProperties(loginrecord,newImpl);
        newImpl.setLogintimeImpl(loginTime);

        return newImpl;
    }
}
