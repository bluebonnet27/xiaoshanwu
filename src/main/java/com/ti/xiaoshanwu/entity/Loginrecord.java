package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Loginrecord)实体类
 *
 * @author makejava
 * @since 2022-04-06 10:28:44
 */
public class Loginrecord implements Serializable {
    private static final long serialVersionUID = -18015277745243905L;
    
    private Integer loginrecordid;
    
    private Date logintime;
    
    private Integer logintype;
    
    private Integer loginid;


    public Integer getLoginrecordid() {
        return loginrecordid;
    }

    public void setLoginrecordid(Integer loginrecordid) {
        this.loginrecordid = loginrecordid;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Integer getLogintype() {
        return logintype;
    }

    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    public Integer getLoginid() {
        return loginid;
    }

    public void setLoginid(Integer loginid) {
        this.loginid = loginid;
    }

}

