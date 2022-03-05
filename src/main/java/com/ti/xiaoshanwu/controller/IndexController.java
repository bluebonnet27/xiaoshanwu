package com.ti.xiaoshanwu.controller;

import com.ti.xiaoshanwu.entity.Admin;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.service.AdminService;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("/ind")
public class IndexController {

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    /**
     * 首页跳转的测试方法.（已废弃）
     *
     * @param session  服务器内的session
     * @param username 用户名
     * @param model    model
     * @return index.html 或者 404
     */
    @RequestMapping("indextest")
    public String goIndex(HttpSession session,
                          String username,
                          Model model){
        if(session.getAttribute("uid")!=null){
            model.addAttribute("uname",username);
            return "index";
        }else {
            model.addAttribute("msg","session空");
            return "messagepage";
        }
    }

    /**
     * 跳转到用户首页.
     *
     * @param session  the session
     * @param model    the model
     * @return the string
     */
    @RequestMapping("userindex")
    public String goUserIndex(HttpSession session,
                          Model model){
        Integer userid = (Integer) session.getAttribute("uid");

        if(userid!=null){
            User user = this.userService.queryById(userid);




            model.addAttribute("user",user);
            return "index";
        }else {
            model.addAttribute("msg","session空");
            return "messagepage";
        }
    }

    /**
     * 跳转到管理员首页.
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @RequestMapping("/adminindex")
    public String goAdminIndex(HttpSession session,
                               Model model){
        if(session.getAttribute("uid")!=null){
            int adminid = (int) session.getAttribute("uid");
            Admin foundAdmin = this.adminService.queryById(adminid);
            if(foundAdmin==null){
                model.addAttribute("msg","IndexController - goAdminIndex - foundAdmin is null");
                return "messagepage";
            }else {
                String adminname = foundAdmin.getAdminname();
                model.addAttribute("adminname",adminname);

                return "admin/adminindex";
            }
        }else {
            model.addAttribute("msg","session空");
            return "messagepage";
        }
    }

    /**
     * 登出系统，销毁session并返回登录页
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @RequestMapping("logout")
    public String goLogout(HttpSession session,
                           Model model){
        session.removeAttribute("uid");
        return "login/loginpage";
    }
}
