package com.digov.api.entity.param.sys.user.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.util.common.CommonUtil;

/**
 * 用户与角色对应关系 单条记录参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysUserRoleIdParam", description = "参数")
public class SysUserRoleIdParam {
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
