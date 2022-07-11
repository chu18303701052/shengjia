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
@ApiModel(value = "FirstUpdateParam", description = "参数")
public class FirstUpdateParam {
    @ApiModelProperty(value = "id", example = "id" )
    private Integer id;
    @ApiModelProperty(value = "名称", example = "名称" )
    private String name;
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
//        if (CommonUtil.isEmpty(id)) {
//            return "id(不能为空)";
//        }
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

        first.setId(id);
        first.setName(name);
        return first;
    }
}
