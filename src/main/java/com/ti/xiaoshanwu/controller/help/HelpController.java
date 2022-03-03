package com.ti.xiaoshanwu.controller.help;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author TiHongsheng
 */
@Controller
@RequestMapping("help")
public class HelpController {

    @RequestMapping("tohelp")
    public String toHelp(HttpSession session, Model model){
        return "help/helpindex";
    }
}
