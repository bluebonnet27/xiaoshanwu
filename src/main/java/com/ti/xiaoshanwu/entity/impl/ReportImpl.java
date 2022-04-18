package com.ti.xiaoshanwu.entity.impl;

import com.ti.xiaoshanwu.entity.Report;

import java.util.Date;

/**
 * The type Report.
 * @author TiHongsheng
 */
public class ReportImpl extends Report {

    private String reporttimeImpl;

    private String reporttypeImpl;

    private String reportuseridNameImpl;

    private String reportstateImpl;

    public String getReporttimeImpl() {
        return reporttimeImpl;
    }

    public void setReporttimeImpl(String reporttimeImpl) {
        this.reporttimeImpl = reporttimeImpl;
    }

    public String getReporttypeImpl() {
        return reporttypeImpl;
    }

    public void setReporttypeImpl(String reporttypeImpl) {
        this.reporttypeImpl = reporttypeImpl;
    }

    public String getReportuseridNameImpl() {
        return reportuseridNameImpl;
    }

    public void setReportuseridNameImpl(String reportuseridNameImpl) {
        this.reportuseridNameImpl = reportuseridNameImpl;
    }

    public String getReportstateImpl() {
        return reportstateImpl;
    }

    public void setReportstateImpl(String reportstateImpl) {
        this.reportstateImpl = reportstateImpl;
    }
}
