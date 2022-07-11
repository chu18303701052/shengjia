package com.digov.api.repository.sys.user.role;

import com.digov.api.entity.db.sys.user.role.SysUserRole;
import com.digov.api.entity.param.sys.user.role.SysUserRoleListParam;
import com.digov.api.entity.result.sys.user.role.SysUserRoleResult;
import com.digov.api.entity.param.sys.user.role.SysUserRoleIdParam;
import com.digov.api.entity.result.sys.user.role.SysUserRoleIdResult;
import com.digov.api.entity.result.sys.user.role.SysUserRoleDetailResult;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户与角色对应关系 Repository 接口
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysUserRoleRepository extends BaseMapper<SysUserRole> {

    /**
     * 分页获取列表
     * @param param
     * @return
     */
    List<SysUserRoleResult> getSysUserRoleResultList(SysUserRoleListParam param);

    /**
     * 获取编辑回显数据
     * @param param
     * @return
     */
    SysUserRoleIdResult getSysUserRoleIdResult(SysUserRoleIdParam param);

    /**
     * 获取列表详情数据
     * @param param
     * @return
     */
    SysUserRoleDetailResult getSysUserRoleDetailResult(SysUserRoleIdParam param);

    List<String> getRoleNameList(@Param("sysUserId") Long sysUserId);
}
