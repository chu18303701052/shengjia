package com.digov.api.entity.result.sys.role.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;

/**
 * 角色与菜单对应关系 新增参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysRoleMenuTempResult", description = "参数")
public class SysRoleMenuTempResult {
    @ApiModelProperty(value = "", example = "" )
    private Long id;
    @ApiModelProperty(value = "角色ID", example = "角色ID" )
    private Long roleId;
    @ApiModelProperty(value = "菜单ID", example = "菜单ID" )
    private Long menuId;
    @ApiModelProperty(value = "是否删除 0：正常 -1：删除", example = "是否删除 0：正常 -1：删除" )
    private Integer flag;

}
