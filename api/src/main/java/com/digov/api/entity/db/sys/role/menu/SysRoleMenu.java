package com.digov.api.entity.db.sys.role.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色与菜单对应关系
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@TableName(value = "sys_role_menu")
public class SysRoleMenu {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 是否删除 0：正常 -1：删除
     */
    private Integer flag;
}
