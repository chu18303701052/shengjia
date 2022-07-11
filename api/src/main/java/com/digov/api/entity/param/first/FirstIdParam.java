package com.digov.api.entity.param.first;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.util.common.CommonUtil;

/**
 *  单条记录参数
 *
 * @author system_code
 * @since 2022-07-11
 */
@Setter
@Getter
@ApiModel(value = "FirstIdParam", description = "参数")
public class FirstIdParam {
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
        if (CommonUtil.isEmpty(id)) {
            return "id 不能为空";
        }
        return null;
    }
}
