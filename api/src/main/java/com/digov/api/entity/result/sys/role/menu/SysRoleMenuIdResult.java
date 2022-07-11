package com.digov.api.entity.result.sys.role.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.entity.db.sys.role.menu.SysRoleMenu;
import com.digov.api.util.common.CommonUtil;
/**
 * 角色与菜单对应关系 单条记录响应结果
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysRoleMenuIdResult", description = "响应结果")
public class SysRoleMenuIdResult {
    @ApiModelProperty(value = "", example = "")
    private Long id;
    @ApiModelProperty(value = "角色ID", example = "角色ID")
    private Long roleId;
    @ApiModelProperty(value = "菜单ID", example = "菜单ID")
    private Long menuId;

}
