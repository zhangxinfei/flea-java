package com.soecode.lyf.service.impl;

import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.PermissionMapper;
import com.soecode.lyf.service.PermissionService;
import com.soecode.lyf.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    //    日志
    final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public ResultModel selectALLPermission(List<Map<String, Object>> permissionList, String permissionName) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                //添加管理员信息
                List<Map<String,Object>> permissionInfluence = permissionMapper.selectALLPermission();
                if(permissionInfluence.size() > 0 ){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    result.setData(permissionInfluence);
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }
}
