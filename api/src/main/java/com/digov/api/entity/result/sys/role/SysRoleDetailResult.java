package com.digov.api.entity.result.sys.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
/**
 * 角色 单条记录参数
 *
 * @author system_code
 * @since 2021-05-06
 */
@Setter
@Getter
@ApiModel(value = "SysRoleDetailResult", description = "参数")
public class SysRoleDetailResult {
    @ApiModelProperty(value = "角色id", example = "角色id")
    private Long id;
    @ApiModelProperty(value = "角色名称", example = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "备注", example = "备注")
    private String remark;
    /**
     * 返回参数处理
     */
    public void hanleResult(){

    }
}
