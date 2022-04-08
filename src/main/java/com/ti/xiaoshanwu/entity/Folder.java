package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Folder)实体类
 *
 * @author makejava
 * @since 2022-04-08 10:23:14
 */
public class Folder implements Serializable {
    private static final long serialVersionUID = 471036415357542706L;
    
    private Integer folderid;
    
    private String foldername;
    
    private Date foldertime;
    
    private Integer folderuser;
    
    private String folderstatement;
    
    private Integer foldercount;


    public Integer getFolderid() {
        return folderid;
    }

    public void setFolderid(Integer folderid) {
        this.folderid = folderid;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public Date getFoldertime() {
        return foldertime;
    }

    public void setFoldertime(Date foldertime) {
        this.foldertime = foldertime;
    }

    public Integer getFolderuser() {
        return folderuser;
    }

    public void setFolderuser(Integer folderuser) {
        this.folderuser = folderuser;
    }

    public String getFolderstatement() {
        return folderstatement;
    }

    public void setFolderstatement(String folderstatement) {
        this.folderstatement = folderstatement;
    }

    public Integer getFoldercount() {
        return foldercount;
    }

    public void setFoldercount(Integer foldercount) {
        this.foldercount = foldercount;
    }

}

