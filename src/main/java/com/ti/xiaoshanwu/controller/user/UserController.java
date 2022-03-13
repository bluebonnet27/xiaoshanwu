package com.ti.xiaoshanwu.controller.user;

import com.ti.xiaoshanwu.controller.tool.HeadImgConverter;
import com.ti.xiaoshanwu.entity.Article;
import com.ti.xiaoshanwu.entity.User;
import com.ti.xiaoshanwu.entity.impl.UserImpl;
import com.ti.xiaoshanwu.entity.tool.JsonResult;
import com.ti.xiaoshanwu.service.ArticleService;
import com.ti.xiaoshanwu.service.ThemeService;
import com.ti.xiaoshanwu.service.UserService;
import com.ti.xiaoshanwu.service.impl.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The type Usercontroller.
 *
 * @author TiHongsheng
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MailService mailService;

    @Resource
    private ArticleService articleService;

    @Resource
    private ThemeService themeService;

    /**
     * 登录页跳转至用户中心.
     *
     * @param session the session
     * @param model   the model
     * @return html地址
     */
    @RequestMapping("tousercenter")
    public String toUserCenter(HttpSession session, Model model){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        User targetUser = userService.queryById(targetUserid);

        HeadImgConverter headImgConverter = new HeadImgConverter();
        String userHeadImgUrl = headImgConverter.imgConvert(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        String userHeadImgBgUrl = headImgConverter.imgConvertBg(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        model.addAttribute("headimg",userHeadImgUrl);
        model.addAttribute("headimgbg",userHeadImgBgUrl);

        Article shefArticle = new Article();

        List<Article> articles = this.articleService.queryArticles(shefArticle);

        int theme__1 = 0;
        int theme_0 = 0;
        int theme_1 = 0;
        int theme_others = 0;

        for (Article article:articles){
            int themeid = article.getArticlethemeid();
            switch (themeid){
                case -1:
                    theme__1 += 1;
                    break;
                case 0:
                    theme_0 += 1;
                    break;
                case 1:
                    theme_1 +=1;
                    break;
                default:
                    theme_others += 1;
                    break;
            }
        }

        model.addAttribute("theme01",theme__1);
        model.addAttribute("theme0",theme_0);
        model.addAttribute("theme1",theme_1);
        model.addAttribute("themeo",theme_others);
        model.addAttribute("articlenum",articles.size());
        model.addAttribute("user",targetUser);
        return "user/usermng";
    }

    /**
     * 跳转到修改密码的页面.
     *
     * @param session the session
     * @param model   the model
     * @return html地址
     */
    @RequestMapping("tochangepwd")
    public String toChangPwd(HttpSession session, Model model){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        User targetUser = userService.queryById(targetUserid);

        HeadImgConverter headImgConverter = new HeadImgConverter();
        String userHeadImgUrl = headImgConverter.imgConvert(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        String userHeadImgBgUrl = headImgConverter.imgConvertBg(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        model.addAttribute("headimg",userHeadImgUrl);
        model.addAttribute("headimgbg",userHeadImgBgUrl);

        model.addAttribute("user",targetUser);
        return "user/userchangepwdpage";
    }

    /**
     * 前端请求验证码.
     *
     * @param session 获取用户，进而获取邮件地址
     * @return the string
     */
    @RequestMapping("getCheckCode")
    public @ResponseBody String getChangeEmailCode(HttpSession session){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        User targetUser = userService.queryById(targetUserid);
        String email = targetUser.getUseremail();
        JsonResult emailCodeBackResult = new JsonResult();

        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(email, "小山屋论坛-验证码", message);
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

    /**
     * 跳转到修改邮箱的页面.
     *
     * @param session the session
     * @param model   the model
     * @return html地址
     */
    @RequestMapping("tochangeemail")
    public String toChangEmail(HttpSession session, Model model){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        User targetUser = userService.queryById(targetUserid);

        HeadImgConverter headImgConverter = new HeadImgConverter();
        String userHeadImgUrl = headImgConverter.imgConvert(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        String userHeadImgBgUrl = headImgConverter.imgConvertBg(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        model.addAttribute("headimg",userHeadImgUrl);
        model.addAttribute("headimgbg",userHeadImgBgUrl);

        model.addAttribute("user",targetUser);
        return "user/userchangeemailpage";
    }

    @RequestMapping("changeemail")
    @ResponseBody
    public String changeEmail(HttpSession session ,
                              String inCode ,
                              String newEmail){
        Integer targetUserid = (Integer) session.getAttribute("uid");
        JsonResult emailBackResult = new JsonResult();

        String serverCode = (String) session.getAttribute("emailcode");
        if(Objects.equals(inCode,serverCode)){
            User newUser = new User();
            newUser.setUserid(targetUserid);
            newUser.setUseremail(newEmail);
            User backUser = this.userService.update(newUser);
            if(backUser==null){
                emailBackResult.setResult(false);
                emailBackResult.setErrMsg("由于数据库的原因，更新失败");
            }else {
                emailBackResult.setResult(true);
                emailBackResult.setResMsg("更新成功");
            }
        }else {
            emailBackResult.setResult(false);
            emailBackResult.setErrMsg("验证码错误，请重新进入此页面获取新的验证码");
        }

        return emailBackResult.toString();
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

        HeadImgConverter headImgConverter = new HeadImgConverter();
        String userHeadImgUrl = headImgConverter.imgConvert(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        String userHeadImgBgUrl = headImgConverter.imgConvertBg(targetUser.getUserimg()==null?0:targetUser.getUserimg());
        model.addAttribute("headimg",userHeadImgUrl);
        model.addAttribute("headimgbg",userHeadImgBgUrl);

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
