package ${package.Controller};

import ${cfg.packageParam}.${entity}IdsParam;
import ${package.Service}.${table.serviceName};
import ${cfg.packageParam}.${entity}AddParam;
import ${cfg.packageParam}.${entity}IdParam;
import ${cfg.packageParam}.${entity}ListParam;
import ${cfg.packageParam}.${entity}UpdateParam;

import ${cfg.packageResult}.${entity}Result;
import ${cfg.packageResult}.${entity}IdResult;
import ${cfg.packageResult}.${entity}DetailResult;

import com.digov.api.entity.result.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import com.digov.api.config.redis.annotation.RepeatSubmit;

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${table.comment!}")
@RestController
@RequestMapping("/pc/${controllerMappingHyphen?replace("-", "/")}")
public class ${table.controllerName} {

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @RepeatSubmit
    @ApiOperation(value = "新增")
    @RequestMapping(value = "/add${entity}AddParam", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<String> add${entity}AddParam(@RequestBody ${entity}AddParam param) {
        //添加用户信息
//        Long sysUserId = ShiroUtil.getUserId();
//        param.setCreateUserId(sysUserId);
        R<String> r = ${table.serviceName?uncap_first}.add${entity}AddParam(param);
        return r;
    }

    @ApiOperation(value = "通过id单条获取")
    @RequestMapping(value = "/get${entity}IdResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<${entity}IdResult> get${entity}IdResult(@RequestBody ${entity}IdParam param) {
        R<${entity}IdResult> r = ${table.serviceName?uncap_first}.get${entity}IdResult(param);
        return r;
    }

    @RepeatSubmit
    @ApiOperation(value = "通过id单条更新数据")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<String> update(@RequestBody ${entity}UpdateParam param) {
        //添加用户信息
        R<String> r = ${table.serviceName?uncap_first}.update(param);
        return r;
    }

    @ApiOperation(value = "列表分页获取")
    @RequestMapping(value = "/get${entity}ListResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<PageInfo<${entity}Result>> get${entity}ListResult(@RequestBody ${entity}ListParam param) {
        R<PageInfo<${entity}Result>> r = ${table.serviceName?uncap_first}.get${entity}ListResult(param);
        return r;
    }

    @ApiOperation(value = "列表单条记录详情")
    @RequestMapping(value = "/get${entity}DetailResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<${entity}DetailResult> get${entity}DetailResult(@RequestBody ${entity}IdParam param) {
        R<${entity}DetailResult> r = ${table.serviceName?uncap_first}.get${entity}DetailResult(param);
        return r;
    }

    @ApiOperation(value = "批量删除数据")
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<String> deleteByIds(@RequestBody ${entity}IdsParam param) {
        R<String> r = ${table.serviceName?uncap_first}.deleteByIds(param);
        return r;
    }
}
