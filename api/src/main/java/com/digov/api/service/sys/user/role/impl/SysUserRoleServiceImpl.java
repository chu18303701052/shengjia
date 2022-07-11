package com.digov.api.service.sys.user.role.impl;

import com.digov.api.entity.result.R;
import com.digov.api.repository.sys.user.role.SysUserRoleRepository;
import com.digov.api.service.sys.user.role.SysUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.digov.api.entity.db.sys.user.role.SysUserRole;
import com.digov.api.entity.param.sys.user.role.SysUserRoleAddParam;
import com.digov.api.entity.param.sys.user.role.SysUserRoleIdParam;
import com.digov.api.entity.param.sys.user.role.SysUserRoleUpdateParam;
import com.digov.api.entity.result.sys.user.role.SysUserRoleIdResult;
import com.digov.api.entity.param.sys.user.role.SysUserRoleListParam;
import com.digov.api.entity.result.sys.user.role.SysUserRoleResult;
import com.digov.api.entity.result.sys.user.role.SysUserRoleDetailResult;
import com.digov.api.util.common.CommonUtil;
import java.util.List;

/**
 * 用户与角色对应关系 服务实现类
 *
 * @author system_code
 * @since 2021-05-06
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    @Override
    public R<String> addSysUserRoleAddParam(SysUserRoleAddParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysUserRole sysUserRole = param.toSysUserRole();
        sysUserRoleRepository.insert(sysUserRole);
        Long insertId = sysUserRole.getId();
        r.setData(String.valueOf(insertId));
        return r;
    }

    @Override
    public R<SysUserRoleIdResult> getSysUserRoleIdResult(SysUserRoleIdParam param) {
        R<SysUserRoleIdResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysUserRoleIdResult sysUserRoleIdResult = sysUserRoleRepository.getSysUserRoleIdResult(param);
        r.setData(sysUserRoleIdResult);
        return r;
    }

    @Override
    public R<PageInfo<SysUserRoleResult>> getSysUserRoleListResult(SysUserRoleListParam param) {
        R<PageInfo<SysUserRoleResult>> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }

        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();
        String sort = param.getSort();
        //分页功能
        PageHelper.startPage(pageNum, pageSize, sort);

        List<SysUserRoleResult> list = sysUserRoleRepository.getSysUserRoleResultList(param);
        PageInfo<SysUserRoleResult> result = new PageInfo<>(list);
        r.setData(result);
        return r;
    }
    @Override
    public R<SysUserRoleDetailResult> getSysUserRoleDetailResult(SysUserRoleIdParam param) {
        R<SysUserRoleDetailResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysUserRoleDetailResult result = sysUserRoleRepository.getSysUserRoleDetailResult(param);
        r.setData(result);
        return r;
    }

    @Override
    public R<String> update(SysUserRoleUpdateParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        Long id = param.getId();
        SysUserRole sysUserRole = sysUserRoleRepository.selectById(id);
        if (CommonUtil.isEmpty(sysUserRole)) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }

        SysUserRole sysUserRoleUpdate = param.toSysUserRole();
        sysUserRoleRepository.updateById(sysUserRoleUpdate);

        return r;
    }
}
