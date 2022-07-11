package com.digov.api.repository.sys.menu;

import com.digov.api.entity.db.sys.menu.SysMenu;
import com.digov.api.entity.param.sys.menu.SysMenuListParam;
import com.digov.api.entity.result.sys.menu.SysMenuResult;
import com.digov.api.entity.param.sys.menu.SysMenuIdParam;
import com.digov.api.entity.result.sys.menu.SysMenuIdResult;
import com.digov.api.entity.result.sys.menu.SysMenuDetailResult;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 菜单管理 Repository 接口
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysMenuRepository extends BaseMapper<SysMenu> {

    /**
     * 分页获取列表
     * @param param
     * @return
     */
    List<SysMenuResult> getSysMenuResultList(SysMenuListParam param);

    /**
     * 获取编辑回显数据
     * @param param
     * @return
     */
    SysMenuIdResult getSysMenuIdResult(SysMenuIdParam param);

    /**
     * 获取列表详情数据
     * @param param
     * @return
     */
    SysMenuDetailResult getSysMenuDetailResult(SysMenuIdParam param);
}
