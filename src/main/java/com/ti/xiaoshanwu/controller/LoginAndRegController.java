package com.ti.xiaoshanwu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ti.xiaoshanwu.entity.Admin;
import com.ti.xiaoshanwu.entity.Board;
import com.ti.xiaoshanwu.entity.Loginrecord;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.AdminService;
import com.ti.xiaoshanwu.service.BoardService;
import com.ti.xiaoshanwu.service.LoginrecordService;
import com.ti.xiaoshanwu.service.UserService;
import com.ti.xiaoshanwu.service.impl.MailService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("/login")
public class LoginAndRegController {

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Resource
    private BoardService boardService;

    @Resource
    private LoginrecordService loginrecordService;

    @Resource
    private MailService mailService;

    @RequestMapping("tologin")
    public String toLogin(Model model){
        //查询最新公告内容
        Board siftBoard = new Board();
        List<Board> boards = this.boardService.queryByNoPage(siftBoard);
        model.addAttribute("board", boards.get(0));
        return "login/loginpage";
    }

    @RequestMapping("toreg")
    public String toReg(){ return "login/regpage"; }

    @RequestMapping("tofindpwdpage")
    public String toFindPwdPage(){ return "login/findpwd";}

    @RequestMapping("reggetcode")
    @ResponseBody
    public String regGetCheckCode(HttpSession session, String testemail){
        JsonResult emailCodeBackResult = new JsonResult();

        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(testemail, "小山屋论坛-验证码", message);
            session.setAttribute("emailcode",checkCode);
            emailCodeBackResult.setResult(true);
            emailCodeBackResult.setResMsg("验证码发送成功");
        }catch (Exception e){
            e.printStackTrace();
            emailCodeBackResult.setResult(false);
            emailCodeBackResult.setErrMsg("验证码发送失败");
        }

        return emailCodeBackResult.toString();
    }

    @RequestMapping("findbackpwd")
    @ResponseBody
    public String regFindPwd(HttpSession session,
                             @RequestParam String newpwd,
                             @RequestParam String uemail,
                             @RequestParam String checkcode){
        JsonResult backResult = new JsonResult();
        User findedUser = this.userService.queryByEmail(uemail);
        if(findedUser==null){
            backResult.setResult(false);
            backResult.setErrMsg("无法找到此邮箱对应的用户，请检查您的邮箱输入是否正确");

            return backResult.toString();
        }else {
            String savedCheckCode = (String) session.getAttribute("emailcode");
            if(checkcode.equals(savedCheckCode)){
                User newUser = new User();
                newUser.setUserid(findedUser.getUserid());
                newUser.setUserpwd(newpwd);

                User backUser = this.userService.update(newUser);

                if(backUser==null){
                    backResult.setResult(false);
                    backResult.setErrMsg("由于数据库的原因，密码更新失败");

                    return backResult.toString();
                }else {
                    backResult.setResult(true);
                    backResult.setResMsg("密码修改成功，将为您跳转到首页");

                    //添加登录记录
                    Loginrecord loginrecord = new Loginrecord();
                    loginrecord.setLogintype(findedUser.getUserrole());
                    loginrecord.setLoginid(findedUser.getUserid());

                    Date now = new Date();
                    loginrecord.setLogintime(now);

                    Loginrecord recordBackResult = this.loginrecordService.insert(loginrecord);

                    //session
                    session.setAttribute("uid",findedUser.getUserid());

                    return backResult.toString();
                }
            }else {
                backResult.setResult(false);
                backResult.setErrMsg("验证码错误");

                return backResult.toString();
            }
        }
    }

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

                //添加登录记录
                Loginrecord loginrecord = new Loginrecord();
                loginrecord.setLogintype(selectedUser.getUserrole());
                loginrecord.setLoginid(selectedUser.getUserid());

                Date now = new Date();
                loginrecord.setLogintime(now);

                Loginrecord backResult = this.loginrecordService.insert(loginrecord);
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

    @RequestMapping("adminlogin")
    public @ResponseBody String adminLogin(HttpSession session,
                                           String adminname,
                                           String adminpwd){
        JsonResult adminQueryBackResult = new JsonResult();
        Admin selectedAdmin = this.adminService.queryByAdminname(adminname);
        if(selectedAdmin==null){
            adminQueryBackResult.setResult(false);
            adminQueryBackResult.setErrMsg("用户不存在！");
        }else {
            String selectedPwd = selectedAdmin.getAdminpwd();
            if(!Objects.equals(adminpwd, selectedPwd)){
                adminQueryBackResult.setResult(false);
                adminQueryBackResult.setErrMsg("密码错误！");
            }else {
                adminQueryBackResult.setResult(true);
                adminQueryBackResult.setResMsg(selectedAdmin.getAdminname());
                session.setAttribute("uid",selectedAdmin.getAdminid());

                //添加登录记录
                Loginrecord loginrecord = new Loginrecord();
                loginrecord.setLogintype(2);
                loginrecord.setLoginid(selectedAdmin.getAdminid());

                Date now = new Date();
                loginrecord.setLogintime(now);

                Loginrecord backResult = this.loginrecordService.insert(loginrecord);
            }
        }
        return adminQueryBackResult.toString();
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
