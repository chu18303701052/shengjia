package com.digov.api.service.sys.role;

import com.digov.api.entity.param.sys.role.SysRoleAddParam;
import com.digov.api.entity.param.sys.role.SysRoleIdParam;
import com.digov.api.entity.param.sys.role.SysRoleUpdateParam;
import com.digov.api.entity.param.sys.role.SysRoleListParam;
import com.digov.api.entity.result.sys.role.SysRoleResult;
import com.digov.api.entity.result.sys.role.SysRoleIdResult;
import com.digov.api.entity.result.sys.role.SysRoleDetailResult;
import com.digov.api.entity.result.R;
import com.github.pagehelper.PageInfo;
/**
 * 角色 服务类
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysRoleService {
    /**
     * 用户表-新增
     *
     * @param param
     * @return
     */
    R<String> addSysRoleAddParam(SysRoleAddParam param);

    /**
     * 用户表-获取单条记录
     *
     * @param param
     * @return
     */
    R<SysRoleIdResult> getSysRoleIdResult(SysRoleIdParam param);

    /**
     * 用户表-信息分页
     *
     * @param param
     * @return
     */
    R<PageInfo<SysRoleResult>> getSysRoleListResult(SysRoleListParam param);

    /**
     * 获取列表详情数据
     *
     * @param param
     * @return
     */
    R<SysRoleDetailResult> getSysRoleDetailResult(SysRoleIdParam param);

    /**
     * 单条数据的更新接口
     *
     * @param param
     * @return
     */
    R<String> update(SysRoleUpdateParam param);
}
