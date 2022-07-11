package com.digov.api.service.sys.user;

import com.digov.api.entity.param.sys.user.SysUserAddParam;
import com.digov.api.entity.param.sys.user.SysUserIdParam;
import com.digov.api.entity.param.sys.user.SysUserUpdateParam;
import com.digov.api.entity.param.sys.user.SysUserListParam;
import com.digov.api.entity.result.sys.user.SysUserResult;
import com.digov.api.entity.result.sys.user.SysUserIdResult;
import com.digov.api.entity.result.sys.user.SysUserDetailResult;
import com.digov.api.entity.result.R;
import com.github.pagehelper.PageInfo;
/**
 * 系统用户 服务类
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysUserService {
    /**
     * 用户表-新增
     *
     * @param param
     * @return
     */
    R<String> addSysUserAddParam(SysUserAddParam param);

    /**
     * 用户表-获取单条记录
     *
     * @param param
     * @return
     */
    R<SysUserIdResult> getSysUserIdResult(SysUserIdParam param);

    /**
     * 用户表-信息分页
     *
     * @param param
     * @return
     */
    R<PageInfo<SysUserResult>> getSysUserListResult(SysUserListParam param);

    /**
     * 获取列表详情数据
     *
     * @param param
     * @return
     */
    R<SysUserDetailResult> getSysUserDetailResult(SysUserIdParam param);

    /**
     * 单条数据的更新接口
     *
     * @param param
     * @return
     */
    R<String> update(SysUserUpdateParam param);
}
