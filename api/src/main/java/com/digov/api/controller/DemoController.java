package com.digov.api.controller;

import com.digov.api.config.exception.CustomException;
import com.digov.api.entity.result.R;
import com.digov.api.util.common.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * 投资项目表 前端控制器
 *
 * @author system_code
 * @since 2020-08-17
 */
@Api(tags = "demo测试接口")
@RestController
@RequestMapping("/pc/demo")
public class DemoController {
@Autowired
private RedisTemplate redisTemplate;
    @ApiOperation(value = "测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public R<String> test() {
        R<String> r = new R<>();
        redisTemplate.opsForValue().set("name","chufduhao");
        String name = redisTemplate.opsForValue().get("name").toString();
        r.setData(name);
        return r;
    }
    @ApiOperation(value = "测试")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public R<String> hello() {
        R<String> r = new R<>();
        r = null;
        String errMsg = r.getErrMsg();
        if (CommonUtil.isEmpty(errMsg)) {
            throw new CustomException("参数错误");
        }

        r.setData("hello world");
        return r;
    }

}
