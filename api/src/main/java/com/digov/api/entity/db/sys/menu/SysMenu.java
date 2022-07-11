package com.digov.api.entity.db.sys.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单管理
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@TableName(value = "sys_menu")
public class SysMenu {

    /**
     * 菜单id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型   1：目录   2：菜单   3：模块 4：隐藏模块
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 当前层级 1-N
     */
    private Integer level;

    /**
     * 前端路由url
     */
    private String routerUrl;

    /**
     * 创建时间毫秒值
     */
    private Long createTime;

    /**
     * 修改时间的毫秒值
     */
    private Long modifyTime;

    /**
     * 是否删除 0：正常 -1：删除
     */
    private Integer flag;
}
