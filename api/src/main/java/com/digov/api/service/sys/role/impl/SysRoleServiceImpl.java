package com.digov.api.service.sys.role.impl;

import com.digov.api.entity.result.R;
import com.digov.api.repository.sys.role.SysRoleRepository;
import com.digov.api.service.sys.role.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.digov.api.entity.db.sys.role.SysRole;
import com.digov.api.entity.param.sys.role.SysRoleAddParam;
import com.digov.api.entity.param.sys.role.SysRoleIdParam;
import com.digov.api.entity.param.sys.role.SysRoleUpdateParam;
import com.digov.api.entity.result.sys.role.SysRoleIdResult;
import com.digov.api.entity.param.sys.role.SysRoleListParam;
import com.digov.api.entity.result.sys.role.SysRoleResult;
import com.digov.api.entity.result.sys.role.SysRoleDetailResult;
import com.digov.api.util.common.CommonUtil;
import java.util.List;

/**
 * 角色 服务实现类
 *
 * @author system_code
 * @since 2021-05-06
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Override
    public R<String> addSysRoleAddParam(SysRoleAddParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysRole sysRole = param.toSysRole();
        sysRoleRepository.insert(sysRole);
        Long insertId = sysRole.getId();
        r.setData(String.valueOf(insertId));
        return r;
    }

    @Override
    public R<SysRoleIdResult> getSysRoleIdResult(SysRoleIdParam param) {
        R<SysRoleIdResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysRoleIdResult sysRoleIdResult = sysRoleRepository.getSysRoleIdResult(param);
        r.setData(sysRoleIdResult);
        return r;
    }

    @Override
    public R<PageInfo<SysRoleResult>> getSysRoleListResult(SysRoleListParam param) {
        R<PageInfo<SysRoleResult>> r = new R<>();
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

        List<SysRoleResult> list = sysRoleRepository.getSysRoleResultList(param);
        PageInfo<SysRoleResult> result = new PageInfo<>(list);
        r.setData(result);
        return r;
    }
    @Override
    public R<SysRoleDetailResult> getSysRoleDetailResult(SysRoleIdParam param) {
        R<SysRoleDetailResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysRoleDetailResult result = sysRoleRepository.getSysRoleDetailResult(param);
        r.setData(result);
        return r;
    }

    @Override
    public R<String> update(SysRoleUpdateParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        Long id = param.getId();
        SysRole sysRole = sysRoleRepository.selectById(id);
        if (CommonUtil.isEmpty(sysRole)) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }

        SysRole sysRoleUpdate = param.toSysRole();
        sysRoleRepository.updateById(sysRoleUpdate);

        return r;
    }
}
