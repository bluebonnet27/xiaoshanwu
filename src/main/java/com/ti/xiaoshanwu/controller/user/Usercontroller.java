package com.ti.xiaoshanwu.controller.user;

import com.ti.xiaoshanwu.entity.User;
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
@RequestMapping("user")
public class Usercontroller {

    @Resource
    private UserService userService;

//    @RequestMapping("userinfo")
//    public String queryUserByid(HttpSession session, Model model){
//        Integer targetUserid = (Integer) session.getAttribute("uid");
//        if(targetUserid==null){
//            mo
//        }
//        User targetUser = userService.queryById(targetUserid);
//    }
}
