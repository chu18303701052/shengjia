package com.digov.api.entity.param.first;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import com.digov.api.util.common.CommonUtil;

/**
 *  多条记录参数
 *
 * @author system_code
 * @since 2022-07-11
 */
@Data
@ApiModel(value = "FirstIdsParam", description = "参数")
public class FirstIdsParam {
    @ApiModelProperty(value = "id集合", example = "[1]")
    private List<Integer> ids;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
        if (CommonUtil.isEmpty(ids)) {
            return "id集合 不能为空";
        }
        return null;
    }
}
