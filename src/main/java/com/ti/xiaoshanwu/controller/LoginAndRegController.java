package com.ti.xiaoshanwu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            checkName.setErrMsg("用户名已被使用！");
            System.out.println("---------------false-------------");
        }else {
            checkName.setResult(true);
            System.out.println("---------------true-------------");
        }

        return checkName.toString();
    }

    /**
     * Reg full string.
     *
     * @param username      the username
     * @param userpwd       the userpwd
     * @param useremail     the useremail
     * @param usersex       the usersex
     * @param userhead      the userhead
     * @param userbirth     the userbirth
     * @param userstatement the userstatement
     * @param session       the session
     * @return the string
     */
    @CrossOrigin
    @RequestMapping("regfull")
    public @ResponseBody String regFull(@RequestParam String username,
                                        @RequestParam String userpwd,
                                        @RequestParam String useremail,
                                        @RequestParam Integer usersex,
                                        @RequestParam Integer userhead,
                                        @RequestParam String userbirth,
                                        @RequestParam String userstatement,
                                        HttpSession session){
        JsonResult backReg = new JsonResult();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ubirth = new Date();
        Date currentTime = new Date();
        try {
            ubirth = dateFormat.parse(userbirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(this.userService.queryByUsername(username)!=null){
            backReg.setResult(false);
            backReg.setErrMsg("此用户名已经存在\n提示：使用检查用户名确认独一无二性");
        }else {
            User addUser = new User(username,userpwd,useremail,ubirth,usersex,userhead,0,userstatement,currentTime);
            User backUser = this.userService.insert(addUser);
            if(backUser!=null){
                backReg.setResult(true);
                backReg.setResMsg("注册成功，您的id为：" + backUser.getUserid());

                session.setAttribute("uid",backUser.getUserid());
            }else {
                backReg.setResult(false);
                backReg.setErrMsg("由于数据库的问题导致注册失败！");
            }
        }

        return backReg.toString();
    }
}
