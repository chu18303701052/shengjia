package com.digov.api.repository.sys.role;

import com.digov.api.entity.db.sys.role.SysRole;
import com.digov.api.entity.param.sys.role.SysRoleListParam;
import com.digov.api.entity.result.sys.role.SysRoleResult;
import com.digov.api.entity.param.sys.role.SysRoleIdParam;
import com.digov.api.entity.result.sys.role.SysRoleIdResult;
import com.digov.api.entity.result.sys.role.SysRoleDetailResult;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 角色 Repository 接口
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysRoleRepository extends BaseMapper<SysRole> {

    /**
     * 分页获取列表
     * @param param
     * @return
     */
    List<SysRoleResult> getSysRoleResultList(SysRoleListParam param);

    /**
     * 获取编辑回显数据
     * @param param
     * @return
     */
    SysRoleIdResult getSysRoleIdResult(SysRoleIdParam param);

    /**
     * 获取列表详情数据
     * @param param
     * @return
     */
    SysRoleDetailResult getSysRoleDetailResult(SysRoleIdParam param);
}
