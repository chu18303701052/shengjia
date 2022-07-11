package com.digov.api.entity.param.sys.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.util.common.CommonUtil;

/**
 * 菜单管理 单条记录参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysMenuIdParam", description = "参数")
public class SysMenuIdParam {
    @ApiModelProperty(value = "菜单id", example = "1")
    private Long id;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
        if (CommonUtil.isEmpty(id)) {
            return "菜单id 不能为空";
        }
        return null;
    }
}
