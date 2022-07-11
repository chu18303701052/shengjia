package com.digov.api.service.sys.role.menu;

import com.digov.api.entity.param.sys.role.menu.SysRoleMenuAddParam;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuIdParam;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuUpdateParam;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuListParam;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuResult;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuIdResult;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuDetailResult;
import com.digov.api.entity.result.R;
import com.github.pagehelper.PageInfo;
/**
 * 角色与菜单对应关系 服务类
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysRoleMenuService {
    /**
     * 用户表-新增
     *
     * @param param
     * @return
     */
    R<String> addSysRoleMenuAddParam(SysRoleMenuAddParam param);

    /**
     * 用户表-获取单条记录
     *
     * @param param
     * @return
     */
    R<SysRoleMenuIdResult> getSysRoleMenuIdResult(SysRoleMenuIdParam param);

    /**
     * 用户表-信息分页
     *
     * @param param
     * @return
     */
    R<PageInfo<SysRoleMenuResult>> getSysRoleMenuListResult(SysRoleMenuListParam param);

    /**
     * 获取列表详情数据
     *
     * @param param
     * @return
     */
    R<SysRoleMenuDetailResult> getSysRoleMenuDetailResult(SysRoleMenuIdParam param);

    /**
     * 单条数据的更新接口
     *
     * @param param
     * @return
     */
    R<String> update(SysRoleMenuUpdateParam param);
}
