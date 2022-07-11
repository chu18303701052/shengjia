package com.digov.api.entity.param.sys.role.menu;

import com.digov.api.entity.db.sys.role.menu.SysRoleMenu;
import com.digov.api.util.common.CommonUtil;
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
@ApiModel(value = "SysRoleMenuTempParam", description = "参数")
public class SysRoleMenuTempParam {
    @ApiModelProperty(value = "", example = "" )
    private Long id;
    @ApiModelProperty(value = "角色ID", example = "角色ID" )
    private Long roleId;
    @ApiModelProperty(value = "菜单ID", example = "菜单ID" )
    private Long menuId;
    @ApiModelProperty(value = "是否删除 0：正常 -1：删除", example = "是否删除 0：正常 -1：删除" )
    private Integer flag;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
//        if (CommonUtil.isEmpty(id)) {
//            return "(不能为空)";
//        }
//        if (CommonUtil.isEmpty(roleId)) {
//            return "角色ID(不能为空)";
//        }
//        if (CommonUtil.isEmpty(menuId)) {
//            return "菜单ID(不能为空)";
//        }
//        if (CommonUtil.isEmpty(flag)) {
//            return "是否删除 0：正常 -1：删除(不能为空)";
//        }
        return null;
    }

    /**
    * 转为DB数据
    * @return
    */
    public SysRoleMenu toSysRoleMenu(){
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        long currentTimeMS = System.currentTimeMillis();
        sysRoleMenu.setId(id);
        sysRoleMenu.setRoleId(roleId);
        sysRoleMenu.setMenuId(menuId);
        sysRoleMenu.setFlag(flag);
        return sysRoleMenu;
    }
}
