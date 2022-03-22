package com.ti.xiaoshanwu.controller.admin;

import com.ti.xiaoshanwu.entity.Admin;
import com.ti.xiaoshanwu.entity.Theme;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.ThemeImpl;
import com.ti.xiaoshanwu.entity.impl.UserImpl;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.AdminService;
import com.ti.xiaoshanwu.service.ThemeService;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("/adm")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    @Resource
    private ThemeService themeService;

    @RequestMapping("tochangepwd")
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

    @RequestMapping("allusersdefault")
    public String getAllUsers(HttpSession session,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit,
                              Model model){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session空");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //程序页转为类页
        page = page -1;
        //筛选条件
        User siftCondition = new User();
        //排序依据
        Sort sort = Sort.by(Sort.Order.desc("userid"));
        //分页请求
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //执行
        Page<User> userPages = this.userService.queryByPage(siftCondition,pageRequest);
        ArrayList<UserImpl> userImpls = new ArrayList<>();

        //发送到前端前二次处理
        List<User> users = userPages.getContent();

        for(User user:users){
            UserImpl userImpl = this.userService.convertUserToUserImpl(user);
            userImpls.add(userImpl);
        }
        model.addAttribute("userpages",userPages);
        model.addAttribute("userImpls",userImpls);

        return "admin/uuser/adminusermng";
    }

    @RequestMapping("allusershead")
    public String getAllUsersHead(HttpSession session,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "5") Integer limit,
                                  Model model){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session空");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //程序页转为类页
        page = page -1;
        //筛选条件
        User siftCondition = new User(1);
        //排序依据
        Sort sort = Sort.by(Sort.Order.desc("userid"));
        //分页请求
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //执行
        Page<User> userPages = this.userService.queryByPage(siftCondition,pageRequest);
        ArrayList<UserImpl> userImpls = new ArrayList<>();

        //发送到前端前二次处理
        List<User> users = userPages.getContent();

        for(User user:users){
            UserImpl userImpl = this.userService.convertUserToUserImpl(user);
            userImpls.add(userImpl);
        }
        model.addAttribute("userpages",userPages);
        model.addAttribute("userImpls",userImpls);

        return "admin/uuser/adminusermng2";
    }

    @RequestMapping("allthemes")
    public String getAllThemes(HttpSession session,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit,
                               Model model){
        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session空");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //程序页转为类页
        page = page -1;
        //筛选条件
        Theme siftCondition = new Theme();
        //排序依据
        Sort sort = Sort.by(Sort.Order.desc("themeid"));
        //分页请求
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        //执行
        Page<Theme> themePages = this.themeService.queryByPage(siftCondition,pageRequest);
        ArrayList<ThemeImpl> themeImpls = new ArrayList<>();

        //发送到前端前二次处理
        List<Theme> themes = themePages.getContent();

        for(Theme theme:themes){
            ThemeImpl themeImpl = this.themeService.convertToThemeImpl(theme);
            themeImpls.add(themeImpl);
        }

        model.addAttribute("themepages",themePages);
        model.addAttribute("themeImpls",themeImpls);
        return "admin/ttheme/adminthememng";
    }

    @RequestMapping("userdata")
    public String getUserData(HttpSession session,
                              Model model){

        if(session.getAttribute("uid")==null){
            model.addAttribute("msg","session空");
            return "messagepage";
        }
        int adminid = (int) session.getAttribute("uid");
        Admin foundAdmin = this.adminService.queryById(adminid);
        model.addAttribute("admin",foundAdmin);

        //性别
        User man = new User();
        man.setUsersex(0);
        User woman = new User();
        woman.setUsersex(1);
        User thirdman = new User();
        thirdman.setUsersex(2);

        long manNum = this.userService.queryUserNum(man);
        long womanNum = this.userService.queryUserNum(woman);
        long thirdmanNum = this.userService.queryUserNum(thirdman);

        model.addAttribute("totaluser",manNum + womanNum + thirdmanNum);
        model.addAttribute("mannum",manNum);
        model.addAttribute("womannum",womanNum);
        model.addAttribute("thirdmannum",thirdmanNum);

        //
        return "admin/uuser/adminusermngdata";
    }
}
