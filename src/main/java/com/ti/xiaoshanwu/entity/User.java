package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-02-28 08:25:14
 */
public class User implements Serializable {
    private static final long serialVersionUID = -79219184228048150L;
    
    private Integer userid;
    
    private String username;
    //无需展示
    private String userpwd;
    //无需展示
    private String useremail;

    private Date userbirth;
    
    private Integer usersex;
    
    private Integer userlv;
    //无需展示？
    private Integer userimg;
    
    private Integer userrole;
    //无需展示
    private String userstatement;
    
    private Date userregtime;
    
    private Integer usermoney;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Date getUserbirth() {
        return userbirth;
    }

    public void setUserbirth(Date userbirth) {
        this.userbirth = userbirth;
    }

    public Integer getUsersex() {
        return usersex;
    }

    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }

    public Integer getUserlv() {
        return userlv;
    }

    public void setUserlv(Integer userlv) {
        this.userlv = userlv;
    }

    public Integer getUserimg() {
        return userimg;
    }

    public void setUserimg(Integer userimg) {
        this.userimg = userimg;
    }

    public Integer getUserrole() {
        return userrole;
    }

    public void setUserrole(Integer userrole) {
        this.userrole = userrole;
    }

    public String getUserstatement() {
        return userstatement;
    }

    public void setUserstatement(String userstatement) {
        this.userstatement = userstatement;
    }

    public Date getUserregtime() {
        return userregtime;
    }

    public void setUserregtime(Date userregtime) {
        this.userregtime = userregtime;
    }

    public Integer getUsermoney() {
        return usermoney;
    }

    public void setUsermoney(Integer usermoney) {
        this.usermoney = usermoney;
    }

    /**
     * 注册时插入新用户的专用构造器
     *
     * @param username      the username
     * @param userpwd       the userpwd
     * @param useremail     the useremail
     * @param userbirth     the userbirth
     * @param usersex       the usersex
     * @param userimg       the userimg
     * @param userrole      the userrole
     * @param userstatement the userstatement
     * @param userregtime   the userregtime
     */
    public User(String username, String userpwd, String useremail, Date userbirth, Integer usersex, Integer userimg, Integer userrole, String userstatement, Date userregtime) {
        this.username = username;
        this.userpwd = userpwd;
        this.useremail = useremail;
        this.userbirth = userbirth;
        this.usersex = usersex;
        this.userimg = userimg;
        this.userrole = userrole;
        this.userstatement = userstatement;
        this.userregtime = userregtime;
    }

    /**
     * 作为用户角色筛选条件的专用构造器.
     *
     * @param userrole the userrole
     */
    public User(Integer userrole) {
        this.userrole = userrole;
    }

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", useremail='" + useremail + '\'' +
                ", userbirth=" + userbirth +
                ", usersex=" + usersex +
                ", userlv=" + userlv +
                ", userimg=" + userimg +
                ", userrole=" + userrole +
                ", userstatement='" + userstatement + '\'' +
                ", userregtime=" + userregtime +
                ", usermoney=" + usermoney +
                '}';
    }

    public String toHtmlString(){
        return "usersex:" + usersex + "\n"
                + "userhead" + userimg + "\n"
                + "userbirth" + userbirth + "\n"
                + "userstatement" + userstatement;
    }
}

