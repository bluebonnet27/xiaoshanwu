package com.ti.xiaoshanwu.service.impl;

import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.dao.UserDao;
import com.ti.xiaoshanwu.entity.impl.UserImpl;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 08:25:14
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer userid) {
        return this.userDao.queryById(userid);
    }

    /**
     * Convert user to user user.
     *
     * @param user the user
     * @return the user
     */
    @Override
    public UserImpl convertUserToUserImpl(User user) {
        UserImpl userimpl = new UserImpl();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String ubirth = dateFormat.format(user.getUserbirth());
        String ureg = dateFormatTime.format(user.getUserregtime());

        String urole = "用户";
        if(user.getUserrole()==1){
            urole = "版主";
        }

        String usex = "男";
        if (user.getUsersex() == 1){
            usex = "女";
        }else if (user.getUsersex() == 2){
            usex = "非二元性别者";
        }

        HeadImgConverter headImgConverter = new HeadImgConverter();
        int userimg = user.getUserimg()!=null?user.getUserimg():0;
        String uimg = headImgConverter.imgConvert(userimg);

        //org.springframework.beans.BeanUtils.copyProperties(父类对象,子类对象);
        copyProperties(user,userimpl);
        userimpl.setUserbirthImpl(ubirth);
        userimpl.setUsersexImpl(usex);
        userimpl.setUserroleImpl(urole);
        userimpl.setUserregtimeImpl(ureg);
        userimpl.setUserimgImpl(uimg);

        return userimpl;
    }

    @Override
    public User queryByEmail(String useremail) { return this.userDao.queryByEmail(useremail); }

    @Override
    public User queryByUsername(String username) { return this.userDao.queryByUsername(username); }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userid) {
        return this.userDao.deleteById(userid) > 0;
    }

    @Override
    public long queryUserNum(User user) {
        return this.userDao.count(user);
    }
}
