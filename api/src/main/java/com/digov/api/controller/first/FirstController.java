package com.digov.api.controller.first;

import com.digov.api.entity.param.first.FirstIdsParam;
import com.digov.api.service.first.FirstService;
import com.digov.api.entity.param.first.FirstAddParam;
import com.digov.api.entity.param.first.FirstIdParam;
import com.digov.api.entity.param.first.FirstListParam;
import com.digov.api.entity.param.first.FirstUpdateParam;

import com.digov.api.entity.result.first.FirstResult;
import com.digov.api.entity.result.first.FirstIdResult;
import com.digov.api.entity.result.first.FirstDetailResult;

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
 *  前端控制器
 *
 * @author system_code
 * @since 2022-07-11
 */
@Api(tags = "")
@RestController
@RequestMapping("/pc/first")
public class FirstController {

    @Resource
    private FirstService firstService;

    @RepeatSubmit
    @ApiOperation(value = "新增")
    @RequestMapping(value = "/addFirstAddParam", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<String> addFirstAddParam(@RequestBody FirstAddParam param) {
        //添加用户信息
//        Long sysUserId = ShiroUtil.getUserId();
//        param.setCreateUserId(sysUserId);
        R<String> r = firstService.addFirstAddParam(param);
        return r;
    }

    @ApiOperation(value = "通过id单条获取")
    @RequestMapping(value = "/getFirstIdResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<FirstIdResult> getFirstIdResult(@RequestBody FirstIdParam param) {
        R<FirstIdResult> r = firstService.getFirstIdResult(param);
        return r;
    }

    @RepeatSubmit
    @ApiOperation(value = "通过id单条更新数据")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<String> update(@RequestBody FirstUpdateParam param) {
        //添加用户信息
        R<String> r = firstService.update(param);
        return r;
    }

    @ApiOperation(value = "列表分页获取")
    @RequestMapping(value = "/getFirstListResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<PageInfo<FirstResult>> getFirstListResult(@RequestBody FirstListParam param) {
        R<PageInfo<FirstResult>> r = firstService.getFirstListResult(param);
        return r;
    }

    @ApiOperation(value = "列表单条记录详情")
    @RequestMapping(value = "/getFirstDetailResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<FirstDetailResult> getFirstDetailResult(@RequestBody FirstIdParam param) {
        R<FirstDetailResult> r = firstService.getFirstDetailResult(param);
        return r;
    }

    @ApiOperation(value = "批量删除数据")
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public R<String> deleteByIds(@RequestBody FirstIdsParam param) {
        R<String> r = firstService.deleteByIds(param);
        return r;
    }
}
