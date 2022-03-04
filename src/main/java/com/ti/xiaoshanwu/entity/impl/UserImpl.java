package com.ti.xiaoshanwu.entity.impl;


import com.ti.xiaoshanwu.entity.User;

import java.util.Date;

public class UserImpl extends User {

    private String userbirthImpl;

    private String usersexImpl;

    private String userroleImpl;

    private String userregtimeImpl;

    private String userimgImpl;

    public String getUserbirthImpl() {
        return userbirthImpl;
    }

    public String getUsersexImpl() {
        return usersexImpl;
    }

    public String getUserroleImpl() {
        return userroleImpl;
    }

    public String getUserregtimeImpl() {
        return userregtimeImpl;
    }

    public String getUserimgImpl() {
        return userimgImpl;
    }

    public void setUserbirthImpl(String userbirthImpl) {
        this.userbirthImpl = userbirthImpl;
    }

    public void setUsersexImpl(String usersexImpl) {
        this.usersexImpl = usersexImpl;
    }

    public void setUserroleImpl(String userroleImpl) {
        this.userroleImpl = userroleImpl;
    }

    public void setUserregtimeImpl(String userregtimeImpl) {
        this.userregtimeImpl = userregtimeImpl;
    }

    public void setUserimgImpl(String userimgImpl) {
        this.userimgImpl = userimgImpl;
    }

    public UserImpl(String username, String userpwd, String useremail, Date userbirth, Integer usersex, Integer userimg, Integer userrole, String userstatement, Date userregtime, String userbirthImpl, String usersexImpl, String userroleImpl, String userregtimeImpl, String userimgImpl) {
        super(username, userpwd, useremail, userbirth, usersex, userimg, userrole, userstatement, userregtime);
        this.userbirthImpl = userbirthImpl;
        this.usersexImpl = usersexImpl;
        this.userroleImpl = userroleImpl;
        this.userregtimeImpl = userregtimeImpl;
        this.userimgImpl = userimgImpl;
    }

    public UserImpl() {
    }

    @Override
    public String toString() {
        return super.toString() + "UserImpl{" +
                "userbirthImpl='" + userbirthImpl + '\'' +
                ", usersexImpl='" + usersexImpl + '\'' +
                ", userroleImpl='" + userroleImpl + '\'' +
                ", userregtimeImpl='" + userregtimeImpl + '\'' +
                '}';
    }
}
