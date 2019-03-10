package com.soecode.lyf.util.entity.qrtz;

import java.io.Serializable;

/**
 * Created by wxl on 2018/7/2.
 */
public class QrtzJobEntity implements Serializable {
    private static final long serialVersionUID = -5901235502674835787L;

    private String job_id;//任务id
    private String job_name;//任务名称
    private String job_group;//任务分组
    private String job_time;//任务运行时间表达式
    private String job_status;//任务运行时间表达式
    private String desc;//任务描述

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_group() {
        return job_group;
    }

    public void setJob_group(String job_group) {
        this.job_group = job_group;
    }

    public String getJob_time() {
        return job_time;
    }

    public void setJob_time(String job_time) {
        this.job_time = job_time;
    }

    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
