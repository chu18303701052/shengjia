package com.digov.api.entity.result.sys.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
/**
 * 菜单管理 单条记录响应结果
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysMenuResult", description = "结果")
public class SysMenuResult {
    @ApiModelProperty(value = "菜单id", example = "菜单id")
    private Long id;
    @ApiModelProperty(value = "父菜单ID，一级菜单为0", example = "父菜单ID，一级菜单为0")
    private Long parentId;
    @ApiModelProperty(value = "菜单名称", example = "菜单名称")
    private String name;
    @ApiModelProperty(value = "菜单URL", example = "菜单URL")
    private String url;
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)", example = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;
    @ApiModelProperty(value = "类型   1：目录   2：菜单   3：模块 4：隐藏模块", example = "类型   1：目录   2：菜单   3：模块 4：隐藏模块")
    private Integer type;
    @ApiModelProperty(value = "菜单图标", example = "菜单图标")
    private String icon;
    @ApiModelProperty(value = "排序", example = "排序")
    private Integer orderNum;
    @ApiModelProperty(value = "当前层级 1-N", example = "当前层级 1-N")
    private Integer level;
    @ApiModelProperty(value = "前端路由url", example = "前端路由url")
    private String routerUrl;
    @ApiModelProperty(value = "创建时间毫秒值", example = "创建时间毫秒值")
    private Long createTime;
    @ApiModelProperty(value = "修改时间的毫秒值", example = "修改时间的毫秒值")
    private Long modifyTime;
}
