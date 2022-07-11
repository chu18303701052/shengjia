package com.digov.api.repository.first;

import com.digov.api.entity.db.first.First;
import com.digov.api.entity.param.first.FirstListParam;
import com.digov.api.entity.result.first.FirstResult;
import com.digov.api.entity.param.first.FirstIdParam;
import com.digov.api.entity.result.first.FirstIdResult;
import com.digov.api.entity.result.first.FirstDetailResult;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Repository 接口
 *
 * @author system_code
 * @since 2022-07-11
 */
public interface FirstRepository extends BaseMapper<First> {

    /**
     * 分页获取列表
     * @param param
     * @return
     */
    List<FirstResult> getFirstResultList(FirstListParam param);

    /**
     * 获取编辑回显数据
     * @param param
     * @return
     */
    FirstIdResult getFirstIdResult(FirstIdParam param);

    /**
     * 获取列表详情数据
     * @param param
     * @return
     */
    FirstDetailResult getFirstDetailResult(FirstIdParam param);
}
