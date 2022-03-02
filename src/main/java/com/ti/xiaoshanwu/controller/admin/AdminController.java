package com.ti.xiaoshanwu.controller.admin;

import com.ti.xiaoshanwu.entity.Admin;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("/adm")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping("/tochangepwd")
    public String toChangePwd(HttpSession session,
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

                return "admin/adminchangepwd";
            }
        }else {
            model.addAttribute("msg","session空");
            return "messagepage";
        }
    }

    @RequestMapping("changepwd")
    public @ResponseBody String changePwd(HttpSession session,
                                          String adminpwd){
        JsonResult changePwdBackResult = new JsonResult();

        if(session.getAttribute("uid")!=null){
            int adminid = (int) session.getAttribute("uid");
            Admin foundAdmin = this.adminService.queryById(adminid);


            if(foundAdmin==null){
                changePwdBackResult.setResult(false);
                changePwdBackResult.setErrMsg("用户信息出错，请重新登录!");

                session.removeAttribute("uid");
            }else {
                Admin updateAdmin = new Admin(adminid,adminpwd);
                Admin newAdmin = adminService.update(updateAdmin);

                changePwdBackResult.setResult(true);
                changePwdBackResult.setResMsg("更改成功，你好：" + newAdmin.getAdminname() + "\n" +
                        "你的登录信息已被清除，请重新登录");

                session.removeAttribute("uid");
            }
        }else {
            changePwdBackResult.setResult(false);
            changePwdBackResult.setErrMsg("用户信息为空，请重新登录!");
        }

        return changePwdBackResult.toString();
    }
}
