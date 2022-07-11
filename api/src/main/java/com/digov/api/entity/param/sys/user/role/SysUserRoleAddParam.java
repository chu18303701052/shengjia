package com.digov.api.entity.param.sys.user.role;

import com.digov.api.entity.db.sys.user.role.SysUserRole;
import com.digov.api.util.common.CommonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
/**
 * 用户与角色对应关系 新增参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysUserRoleAddParam", description = "参数")
public class SysUserRoleAddParam {
    @ApiModelProperty(value = "用户ID", example = "用户ID" )
    private Long userId;
    @ApiModelProperty(value = "角色ID", example = "角色ID" )
    private Long roleId;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
//        if (CommonUtil.isEmpty(userId)) {
//            return "用户ID(不能为空)";
//        }
//        if (CommonUtil.isEmpty(roleId)) {
//            return "角色ID(不能为空)";
//        }
        return null;
    }

    /**
    * 转为DB数据
    * @return
    */
    public SysUserRole toSysUserRole(){
        SysUserRole sysUserRole = new SysUserRole();
        long currentTimeMS = System.currentTimeMillis();


        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        return sysUserRole;
    }
}
