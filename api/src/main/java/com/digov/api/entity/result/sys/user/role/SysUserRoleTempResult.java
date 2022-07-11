package com.digov.api.entity.result.sys.user.role;

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
@ApiModel(value = "SysUserRoleTempResult", description = "参数")
public class SysUserRoleTempResult {
    @ApiModelProperty(value = "", example = "" )
    private Long id;
    @ApiModelProperty(value = "用户ID", example = "用户ID" )
    private Long userId;
    @ApiModelProperty(value = "角色ID", example = "角色ID" )
    private Long roleId;
    @ApiModelProperty(value = "是否删除 0：正常 -1：删除", example = "是否删除 0：正常 -1：删除" )
    private Integer flag;

}
