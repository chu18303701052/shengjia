package com.digov.api.repository.sys.role.menu;

import com.digov.api.entity.db.sys.role.menu.SysRoleMenu;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuListParam;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuResult;
import com.digov.api.entity.param.sys.role.menu.SysRoleMenuIdParam;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuIdResult;
import com.digov.api.entity.result.sys.role.menu.SysRoleMenuDetailResult;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色与菜单对应关系 Repository 接口
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysRoleMenuRepository extends BaseMapper<SysRoleMenu> {

    /**
     * 分页获取列表
     * @param param
     * @return
     */
    List<SysRoleMenuResult> getSysRoleMenuResultList(SysRoleMenuListParam param);

    /**
     * 获取编辑回显数据
     * @param param
     * @return
     */
    SysRoleMenuIdResult getSysRoleMenuIdResult(SysRoleMenuIdParam param);

    /**
     * 获取列表详情数据
     * @param param
     * @return
     */
    SysRoleMenuDetailResult getSysRoleMenuDetailResult(SysRoleMenuIdParam param);

    List<String> getPermissionUrlList(@Param("sysUserId") Long sysUserId);
}
