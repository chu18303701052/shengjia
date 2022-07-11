package com.digov.api.entity.param.sys.menu;

import com.digov.api.entity.db.sys.menu.SysMenu;
import com.digov.api.util.common.CommonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;

/**
 * 菜单管理 新增参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysMenuUpdateParam", description = "参数")
public class SysMenuUpdateParam {
    @ApiModelProperty(value = "菜单id", example = "菜单id" )
    private Long id;
    @ApiModelProperty(value = "父菜单ID，一级菜单为0", example = "父菜单ID，一级菜单为0" )
    private Long parentId;
    @ApiModelProperty(value = "菜单名称", example = "菜单名称" )
    private String name;
    @ApiModelProperty(value = "菜单URL", example = "菜单URL" )
    private String url;
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)", example = "授权(多个用逗号分隔，如：user:list,user:create)" )
    private String perms;
    @ApiModelProperty(value = "类型   1：目录   2：菜单   3：模块 4：隐藏模块", example = "类型   1：目录   2：菜单   3：模块 4：隐藏模块" )
    private Integer type;
    @ApiModelProperty(value = "菜单图标", example = "菜单图标" )
    private String icon;
    @ApiModelProperty(value = "排序", example = "排序" )
    private Integer orderNum;
    @ApiModelProperty(value = "当前层级 1-N", example = "当前层级 1-N" )
    private Integer level;
    @ApiModelProperty(value = "前端路由url", example = "前端路由url" )
    private String routerUrl;
    @ApiModelProperty(value = "修改时间的毫秒值", example = "修改时间的毫秒值" , hidden = true)
    private Long modifyTime;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
//        if (CommonUtil.isEmpty(id)) {
//            return "菜单id(不能为空)";
//        }
//        if (CommonUtil.isEmpty(parentId)) {
//            return "父菜单ID，一级菜单为0(不能为空)";
//        }
//        if (CommonUtil.isEmpty(name)) {
//            return "菜单名称(不能为空)";
//        }
//        if (CommonUtil.isEmpty(url)) {
//            return "菜单URL(不能为空)";
//        }
//        if (CommonUtil.isEmpty(perms)) {
//            return "授权(多个用逗号分隔，如：user:list,user:create)(不能为空)";
//        }
//        if (CommonUtil.isEmpty(type)) {
//            return "类型   1：目录   2：菜单   3：模块 4：隐藏模块(不能为空)";
//        }
//        if (CommonUtil.isEmpty(icon)) {
//            return "菜单图标(不能为空)";
//        }
//        if (CommonUtil.isEmpty(orderNum)) {
//            return "排序(不能为空)";
//        }
//        if (CommonUtil.isEmpty(level)) {
//            return "当前层级 1-N(不能为空)";
//        }
//        if (CommonUtil.isEmpty(routerUrl)) {
//            return "前端路由url(不能为空)";
//        }
        return null;
    }

    /**
    * 转为DB数据
    * @return
    */
    public SysMenu toSysMenu(){
        SysMenu sysMenu = new SysMenu();
        long currentTimeMS = System.currentTimeMillis();
        sysMenu.setId(id);
        sysMenu.setParentId(parentId);
        sysMenu.setName(name);
        sysMenu.setUrl(url);
        sysMenu.setPerms(perms);
        sysMenu.setType(type);
        sysMenu.setIcon(icon);
        sysMenu.setOrderNum(orderNum);
        sysMenu.setLevel(level);
        sysMenu.setRouterUrl(routerUrl);
        sysMenu.setModifyTime(currentTimeMS);
        return sysMenu;
    }
}
