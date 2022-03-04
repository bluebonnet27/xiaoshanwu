package com.ti.xiaoshanwu.controller.user;

import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.UserImpl;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * The type Usercontroller.
 *
 * @author TiHongsheng
 */
@Controller
@RequestMapping("user")
public class Usercontroller {

    @Resource
    private UserService userService;

    @RequestMapping("tochangepwd")
    public String toChangPwd(HttpSession session, Model model){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        User targetUser = userService.queryById(targetUserid);

        model.addAttribute("user",targetUser);
        return "user/userchangepwdpage";
    }

    @RequestMapping("tochangeemail")
    public String toChangEmail(HttpSession session, Model model){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        User targetUser = userService.queryById(targetUserid);

        model.addAttribute("user",targetUser);
        return "user/userchangeemailpage";
    }

    @RequestMapping("userinfo")
    public String queryUserByid(HttpSession session, Model model){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        if(targetUserid==null){
            model.addAttribute("errtitle","Uid is null");
            model.addAttribute("errsubtitle","targetuid is null");
            model.addAttribute("errtext","");

            return "errorhandle";
        }
        User targetUser = userService.queryById(targetUserid);
        UserImpl targetUserImpl = userService.convertUserToUserImpl(targetUser);

        model.addAttribute("user",targetUserImpl);
        return "user/userinfopage";
    }

    @RequestMapping("userchangepwd")
    public @ResponseBody String changePwdById(String newpwd,
                                              HttpSession session,
                                              Model model){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        JsonResult changePwdResult = new JsonResult();

        if(targetUserid==null){
            changePwdResult.setResult(false);
            changePwdResult.setErrMsg("登录信息失效，请重新登录！");
        }else {
            User targetUser = this.userService.queryById(targetUserid);
            if(targetUser==null){
                changePwdResult.setResult(false);
                changePwdResult.setErrMsg("出错了，用户信息为空，请联系开发者！");
            }else {
                User newUser = new User();
                newUser.setUserid(targetUserid);
                newUser.setUserpwd(newpwd);

                User backUser = this.userService.update(newUser);
                if(backUser==null){
                    changePwdResult.setResult(false);
                    changePwdResult.setErrMsg("出错了，由于数据库的原因更新用户失败，请联系开发者！");
                }else {
                    session.removeAttribute("uid");

                    changePwdResult.setResult(true);
                    changePwdResult.setResMsg("密码修改成功，请重新登录！");
                }
            }
        }

        return changePwdResult.toString();
    }
}
