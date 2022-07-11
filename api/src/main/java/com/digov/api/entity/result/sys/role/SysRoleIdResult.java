package com.digov.api.entity.result.sys.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.entity.db.sys.role.SysRole;
import com.digov.api.util.common.CommonUtil;
/**
 * 角色 单条记录响应结果
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysRoleIdResult", description = "响应结果")
public class SysRoleIdResult {
    @ApiModelProperty(value = "角色id", example = "角色id")
    private Long id;
    @ApiModelProperty(value = "角色名称", example = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;

}
