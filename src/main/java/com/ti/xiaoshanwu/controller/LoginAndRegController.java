package com.ti.xiaoshanwu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("/login")
public class LoginAndRegController {

    @Resource
    private UserService userService;

    @RequestMapping("tologin")
    public String toLogin(){
        return "login/loginpage";
    }

    @RequestMapping("toreg")
    public String toReg(){ return "login/regpage"; }

    @RequestMapping("testlogin")
    public @ResponseBody String getUserByUseremailAndUserpwd(HttpSession session,
                                                            String email,
                                                            String pwd){
        JsonResult loginBackResult = new JsonResult();
        User selectedUser = this.userService.queryByEmail(email);
        if (selectedUser!=null){
            if(Objects.equals(pwd, selectedUser.getUserpwd())){
                loginBackResult.setResult(true);
                loginBackResult.setResMsg(selectedUser.getUsername());

                session.setAttribute("uid",selectedUser.getUserid());
            }else{
                loginBackResult.setResult(false);
                loginBackResult.setErrMsg("密码错误");
            }
        }else {
            loginBackResult.setResult(false);
            loginBackResult.setErrMsg("用户不存在！");
        }

        return loginBackResult.toString();
    }

    @RequestMapping("checkname")
    public @ResponseBody String checkIfNameExisted(@RequestParam String uname){
        JsonResult checkName = new JsonResult();
        if(this.userService.queryByUsername(uname)!=null){
            checkName.setResult(false);
            checkName.setErrMsg("冲突的用户信息!");
            System.out.println("---------------false-------------");
        }else {
            checkName.setResult(true);
            System.out.println("---------------true-------------");
        }

        return checkName.toString();
    }
}
