package com.digov.api.service.sys.user.impl;

import com.digov.api.entity.result.R;
import com.digov.api.repository.sys.user.SysUserRepository;
import com.digov.api.service.sys.user.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.digov.api.entity.db.sys.user.SysUser;
import com.digov.api.entity.param.sys.user.SysUserAddParam;
import com.digov.api.entity.param.sys.user.SysUserIdParam;
import com.digov.api.entity.param.sys.user.SysUserUpdateParam;
import com.digov.api.entity.result.sys.user.SysUserIdResult;
import com.digov.api.entity.param.sys.user.SysUserListParam;
import com.digov.api.entity.result.sys.user.SysUserResult;
import com.digov.api.entity.result.sys.user.SysUserDetailResult;
import com.digov.api.util.common.CommonUtil;
import java.util.List;

/**
 * 系统用户 服务实现类
 *
 * @author system_code
 * @since 2021-05-06
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public R<String> addSysUserAddParam(SysUserAddParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysUser sysUser = param.toSysUser();
        sysUserRepository.insert(sysUser);
        Long insertId = sysUser.getId();
        r.setData(String.valueOf(insertId));
        return r;
    }

    @Override
    public R<SysUserIdResult> getSysUserIdResult(SysUserIdParam param) {
        R<SysUserIdResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysUserIdResult sysUserIdResult = sysUserRepository.getSysUserIdResult(param);
        r.setData(sysUserIdResult);
        return r;
    }

    @Override
    public R<PageInfo<SysUserResult>> getSysUserListResult(SysUserListParam param) {
        R<PageInfo<SysUserResult>> r = new R<>();
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

        List<SysUserResult> list = sysUserRepository.getSysUserResultList(param);
        PageInfo<SysUserResult> result = new PageInfo<>(list);
        r.setData(result);
        return r;
    }
    @Override
    public R<SysUserDetailResult> getSysUserDetailResult(SysUserIdParam param) {
        R<SysUserDetailResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        SysUserDetailResult result = sysUserRepository.getSysUserDetailResult(param);
        r.setData(result);
        return r;
    }

    @Override
    public R<String> update(SysUserUpdateParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        Long id = param.getId();
        SysUser sysUser = sysUserRepository.selectById(id);
        if (CommonUtil.isEmpty(sysUser)) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }

        SysUser sysUserUpdate = param.toSysUser();
        sysUserRepository.updateById(sysUserUpdate);

        return r;
    }
}
