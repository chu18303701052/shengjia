package com.digov.api.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.digov.api.util.time.DateUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @program: sjx_manage_api
 * @description: mybatis-plus自动填充配置
 * @author: zhangjiajun
 * @create: 2021/04/29
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        Object modifyTime = getFieldValByName("modifyTime", metaObject);
        if (null == createTime) {
            setFieldValByName("createTime", DateUtil.getCurrentTimeMS(), metaObject);
        }
        if (null == modifyTime) {
            setFieldValByName("modifyTime", DateUtil.getCurrentTimeMS(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object modifyTime = getFieldValByName("modifyTime", metaObject);
        if (null == modifyTime) {
            setFieldValByName("modifyTime", DateUtil.getCurrentTimeMS(), metaObject);
        }
    }
}
