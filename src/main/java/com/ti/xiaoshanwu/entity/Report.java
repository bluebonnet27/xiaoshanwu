package com.ti.xiaoshanwu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Report)实体类
 *
 * @author makejava
 * @since 2022-03-31 20:26:58
 */
public class Report implements Serializable {
    private static final long serialVersionUID = -79923455387159910L;
    
    private Integer reportid;
    
    private Integer reporttype;
    
    private Date reporttime;
    
    private String reportreason;
    
    private Integer reportuserid;
    
    private Integer reporttoid;
    
    private Integer reportstate;


    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public Integer getReporttype() {
        return reporttype;
    }

    public void setReporttype(Integer reporttype) {
        this.reporttype = reporttype;
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public String getReportreason() {
        return reportreason;
    }

    public void setReportreason(String reportreason) {
        this.reportreason = reportreason;
    }

    public Integer getReportuserid() {
        return reportuserid;
    }

    public void setReportuserid(Integer reportuserid) {
        this.reportuserid = reportuserid;
    }

    public Integer getReporttoid() {
        return reporttoid;
    }

    public void setReporttoid(Integer reporttoid) {
        this.reporttoid = reporttoid;
    }

    public Integer getReportstate() {
        return reportstate;
    }

    public void setReportstate(Integer reportstate) {
        this.reportstate = reportstate;
    }

    public Report() {
    }

    public Report(Integer reportid, Integer reporttype, Date reporttime, String reportreason, Integer reportuserid, Integer reporttoid, Integer reportstate) {
        this.reportid = reportid;
        this.reporttype = reporttype;
        this.reporttime = reporttime;
        this.reportreason = reportreason;
        this.reportuserid = reportuserid;
        this.reporttoid = reporttoid;
        this.reportstate = reportstate;
    }

    public Report(Integer reporttype, Date reporttime, String reportreason, Integer reportuserid, Integer reporttoid, Integer reportstate) {
        this.reporttype = reporttype;
        this.reporttime = reporttime;
        this.reportreason = reportreason;
        this.reportuserid = reportuserid;
        this.reporttoid = reporttoid;
        this.reportstate = reportstate;
    }
}

