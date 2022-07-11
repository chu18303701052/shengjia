package com.digov.api.service.first;

import com.digov.api.entity.param.first.FirstAddParam;
import com.digov.api.entity.param.first.FirstIdParam;
import com.digov.api.entity.param.first.FirstIdsParam;
import com.digov.api.entity.param.first.FirstUpdateParam;
import com.digov.api.entity.param.first.FirstListParam;
import com.digov.api.entity.result.first.FirstResult;
import com.digov.api.entity.result.first.FirstIdResult;
import com.digov.api.entity.result.first.FirstDetailResult;
import com.digov.api.entity.result.R;
import com.github.pagehelper.PageInfo;
/**
 *  服务类
 *
 * @author system_code
 * @since 2022-07-11
 */
public interface FirstService {
    /**
     * 用户表-新增
     *
     * @param param
     * @return
     */
    R<String> addFirstAddParam(FirstAddParam param);

    /**
     * 用户表-获取单条记录
     *
     * @param param
     * @return
     */
    R<FirstIdResult> getFirstIdResult(FirstIdParam param);

    /**
     * 用户表-信息分页
     *
     * @param param
     * @return
     */
    R<PageInfo<FirstResult>> getFirstListResult(FirstListParam param);

    /**
     * 获取列表详情数据
     *
     * @param param
     * @return
     */
    R<FirstDetailResult> getFirstDetailResult(FirstIdParam param);

    /**
     * 单条数据的更新接口
     *
     * @param param
     * @return
     */
    R<String> update(FirstUpdateParam param);
    
    /**
    * 多条数据的删除接口
    *
    * @param param
    * @return
    */
    R<String> deleteByIds(FirstIdsParam param);
}
