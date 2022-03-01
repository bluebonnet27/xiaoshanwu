package com.ti.xiaoshanwu.controller;

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
@RequestMapping("/ind")
public class IndexController {

    @Resource
    private UserService userService;

    @RequestMapping("indextest")
    public String goIndex(HttpSession session,
                          String username,
                          Model model){
        if(session.getAttribute("uid")!=null){
            int userid = (int) session.getAttribute("uid");
            User currentUser = this.userService.queryById(userid);
            System.out.println("----------------INFO--------------");
            System.out.println(currentUser.toString());

            model.addAttribute("uname",username);
            return "index";
        }else {
            System.out.println("-------------WRONG----------------");
            model.addAttribute("msg","session空");
            return "messagepage";
        }
    }
}
