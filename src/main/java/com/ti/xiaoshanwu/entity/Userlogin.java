package com.ti.xiaoshanwu.entity;

import java.io.Serializable;

/**
 * (Userlogin)实体类
 *
 * @author makejava
 * @since 2022-02-22 12:24:11
 */
public class Userlogin implements Serializable {
    private static final long serialVersionUID = -11754187662606949L;
    
    private String userid;
    
    private String userpwd;
    
    private Integer userrole;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public Integer getUserrole() {
        return userrole;
    }

    public void setUserrole(Integer userrole) {
        this.userrole = userrole;
    }

}

