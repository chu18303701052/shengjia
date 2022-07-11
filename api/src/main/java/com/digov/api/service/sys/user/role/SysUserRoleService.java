package com.digov.api.service.sys.user.role;

import com.digov.api.entity.param.sys.user.role.SysUserRoleAddParam;
import com.digov.api.entity.param.sys.user.role.SysUserRoleIdParam;
import com.digov.api.entity.param.sys.user.role.SysUserRoleUpdateParam;
import com.digov.api.entity.param.sys.user.role.SysUserRoleListParam;
import com.digov.api.entity.result.sys.user.role.SysUserRoleResult;
import com.digov.api.entity.result.sys.user.role.SysUserRoleIdResult;
import com.digov.api.entity.result.sys.user.role.SysUserRoleDetailResult;
import com.digov.api.entity.result.R;
import com.github.pagehelper.PageInfo;
/**
 * 用户与角色对应关系 服务类
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysUserRoleService {
    /**
     * 用户表-新增
     *
     * @param param
     * @return
     */
    R<String> addSysUserRoleAddParam(SysUserRoleAddParam param);

    /**
     * 用户表-获取单条记录
     *
     * @param param
     * @return
     */
    R<SysUserRoleIdResult> getSysUserRoleIdResult(SysUserRoleIdParam param);

    /**
     * 用户表-信息分页
     *
     * @param param
     * @return
     */
    R<PageInfo<SysUserRoleResult>> getSysUserRoleListResult(SysUserRoleListParam param);

    /**
     * 获取列表详情数据
     *
     * @param param
     * @return
     */
    R<SysUserRoleDetailResult> getSysUserRoleDetailResult(SysUserRoleIdParam param);

    /**
     * 单条数据的更新接口
     *
     * @param param
     * @return
     */
    R<String> update(SysUserRoleUpdateParam param);
}
