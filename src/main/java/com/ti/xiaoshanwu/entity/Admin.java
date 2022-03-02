package com.ti.xiaoshanwu.entity;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2022-03-01 15:28:13
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 971076356094390358L;
    
    private Integer adminid;
    
    private String adminname;
    
    private String adminpwd;


    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    /**
     * 更新密码的构造函数
     *
     * @param adminid  the adminid
     * @param adminpwd the adminpwd
     */
    public Admin(Integer adminid, String adminpwd) {
        this.adminid = adminid;
        this.adminpwd = adminpwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                ", adminname='" + adminname + '\'' +
                ", adminpwd='" + adminpwd + '\'' +
                '}';
    }
}

