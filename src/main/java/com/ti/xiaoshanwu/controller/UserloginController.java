package com.ti.xiaoshanwu.controller;

import com.ti.xiaoshanwu.entity.Userlogin;
import com.ti.xiaoshanwu.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * (Userlogin)表控制层
 *
 * @author makejava
 * @since 2022-02-22 12:32:35
 */
@Controller
@RequestMapping("/userlogin")
public class UserloginController {
    /**
     * 服务对象
     */
    @Resource
    private UserloginService userloginService;

    /**
     * Get user login by userid and userpwd string.
     *
     * @param userid  the userid
     * @param userpwd the userpwd
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @RequestMapping("/login")
    public String getUserLoginByUseridAndUserpwd(String userid,
                                                 String userpwd,
                                                 HttpSession session,
                                                 Model model){
        Userlogin userlogin = userloginService.queryById(userid);

        String upwd = userlogin.getUserpwd();
        if(!Objects.equals(upwd, userpwd)){
            model.addAttribute("msg","Password ERROR!");
        }else {
            model.addAttribute("msg","LOGIN SUCCESS!");
        }

        return "messagepage";
    }

    /**
     * 分页查询
     *
     * @param userlogin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Userlogin>> queryByPage(Userlogin userlogin, PageRequest pageRequest) {
        return ResponseEntity.ok(this.userloginService.queryByPage(userlogin, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Userlogin> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userloginService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param userlogin 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Userlogin> add(Userlogin userlogin) {
        return ResponseEntity.ok(this.userloginService.insert(userlogin));
    }

    /**
     * 编辑数据
     *
     * @param userlogin 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Userlogin> edit(Userlogin userlogin) {
        return ResponseEntity.ok(this.userloginService.update(userlogin));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.userloginService.deleteById(id));
    }

}

