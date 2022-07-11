package com.digov.api.repository.sys.user;

import com.digov.api.entity.db.sys.user.SysUser;
import com.digov.api.entity.param.sys.user.SysUserListParam;
import com.digov.api.entity.result.sys.user.SysUserResult;
import com.digov.api.entity.param.sys.user.SysUserIdParam;
import com.digov.api.entity.result.sys.user.SysUserIdResult;
import com.digov.api.entity.result.sys.user.SysUserDetailResult;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 系统用户 Repository 接口
 *
 * @author system_code
 * @since 2021-05-06
 */
public interface SysUserRepository extends BaseMapper<SysUser> {

    /**
     * 分页获取列表
     * @param param
     * @return
     */
    List<SysUserResult> getSysUserResultList(SysUserListParam param);

    /**
     * 获取编辑回显数据
     * @param param
     * @return
     */
    SysUserIdResult getSysUserIdResult(SysUserIdParam param);

    /**
     * 获取列表详情数据
     * @param param
     * @return
     */
    SysUserDetailResult getSysUserDetailResult(SysUserIdParam param);
}
