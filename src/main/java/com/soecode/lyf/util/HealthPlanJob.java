package com.soecode.lyf.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * Created by Administrator on 2017/12/20.
 */
@Component
public class HealthPlanJob extends QuartzJobBean {

//    @Autowired
//    UserMapper userMapper;

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date());

    }


}

