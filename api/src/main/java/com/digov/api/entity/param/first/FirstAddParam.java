package com.digov.api.entity.param.first;

import com.digov.api.entity.db.first.First;
import com.digov.api.util.common.CommonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
/**
 *  新增参数
 *
 * @author system_code
 * @since 2022-07-11
 */
@Setter
@Getter
@ApiModel(value = "FirstAddParam", description = "参数")
public class FirstAddParam {
    @ApiModelProperty(value = "名称", example = "名称" )
    private String name;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
//        if (CommonUtil.isEmpty(name)) {
//            return "名称(不能为空)";
//        }
        return null;
    }

    /**
    * 转为DB数据
    * @return
    */
    public First toFirst(){
        First first = new First();


        first.setName(name);
        return first;
    }
}
