package com.digov.api.entity.result.sys.user.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.entity.db.sys.user.role.SysUserRole;
import com.digov.api.util.common.CommonUtil;
/**
 * 用户与角色对应关系 单条记录响应结果
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysUserRoleIdResult", description = "响应结果")
public class SysUserRoleIdResult {
    @ApiModelProperty(value = "", example = "")
    private Long id;
    @ApiModelProperty(value = "用户ID", example = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "角色ID", example = "角色ID")
    private Long roleId;

}
