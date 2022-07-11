package com.digov.api.service.sys.menu;

import com.digov.api.entity.param.sys.menu.SysMenuAddParam;
import com.digov.api.entity.param.sys.menu.SysMenuIdParam;
import com.digov.api.entity.param.sys.menu.SysMenuUpdateParam;
import com.digov.api.entity.param.sys.menu.SysMenuListParam;
import com.digov.api.entity.result.sys.menu.SysMenuResult;
import com.digov.api.entity.result.sys.menu.SysMenuIdResult;
import com.digov.api.entity.result.sys.menu.SysMenuDetailResult;
import com.digov.api.entity.result.R;
import com.github.pagehelper.PageInfo;
/**
 * 菜单管理 服务类
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysMenuService {
    /**
     * 用户表-新增
     *
     * @param param
     * @return
     */
    R<String> addSysMenuAddParam(SysMenuAddParam param);

    /**
     * 用户表-获取单条记录
     *
     * @param param
     * @return
     */
    R<SysMenuIdResult> getSysMenuIdResult(SysMenuIdParam param);

    /**
     * 用户表-信息分页
     *
     * @param param
     * @return
     */
    R<PageInfo<SysMenuResult>> getSysMenuListResult(SysMenuListParam param);

    /**
     * 获取列表详情数据
     *
     * @param param
     * @return
     */
    R<SysMenuDetailResult> getSysMenuDetailResult(SysMenuIdParam param);

    /**
     * 单条数据的更新接口
     *
     * @param param
     * @return
     */
    R<String> update(SysMenuUpdateParam param);
}
