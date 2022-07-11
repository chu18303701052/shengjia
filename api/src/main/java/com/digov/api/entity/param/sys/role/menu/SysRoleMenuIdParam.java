package com.digov.api.entity.param.sys.role.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.util.common.CommonUtil;

/**
 * 角色与菜单对应关系 单条记录参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysRoleMenuIdParam", description = "参数")
public class SysRoleMenuIdParam {
    @ApiModelProperty(value = "", example = "1")
    private Long id;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
        if (CommonUtil.isEmpty(id)) {
            return " 不能为空";
        }
        return null;
    }
}
