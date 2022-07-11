package com.digov.api.service.first.impl;

import com.digov.api.entity.result.R;
import com.digov.api.repository.first.FirstRepository;
import com.digov.api.service.first.FirstService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.digov.api.entity.db.first.First;
import com.digov.api.entity.param.first.FirstAddParam;
import com.digov.api.entity.param.first.FirstIdParam;
import com.digov.api.entity.param.first.FirstIdsParam;
import com.digov.api.entity.param.first.FirstUpdateParam;
import com.digov.api.entity.result.first.FirstIdResult;
import com.digov.api.entity.param.first.FirstListParam;
import com.digov.api.entity.result.first.FirstResult;
import com.digov.api.entity.result.first.FirstDetailResult;
import com.digov.api.util.common.CommonUtil;
import java.util.List;

/**
 *  服务实现类
 *
 * @author system_code
 * @since 2022-07-11
 */
@Service
public class FirstServiceImpl implements FirstService {

    @Resource
    private FirstRepository firstRepository;

    @Override
    public R<String> addFirstAddParam(FirstAddParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        First first = param.toFirst();
        firstRepository.insert(first);
        Integer insertId = first.getId();
        r.setData(String.valueOf(insertId));
        return r;
    }

    @Override
    public R<FirstIdResult> getFirstIdResult(FirstIdParam param) {
        R<FirstIdResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        FirstIdResult firstIdResult = firstRepository.getFirstIdResult(param);
        r.setData(firstIdResult);
        return r;
    }

    @Override
    public R<PageInfo<FirstResult>> getFirstListResult(FirstListParam param) {
        R<PageInfo<FirstResult>> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }

        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();
        String sort = param.getSort();
        //分页功能
        PageHelper.startPage(pageNum, pageSize, sort);

        List<FirstResult> list = firstRepository.getFirstResultList(param);
        PageInfo<FirstResult> result = new PageInfo<>(list);
        r.setData(result);
        return r;
    }
    @Override
    public R<FirstDetailResult> getFirstDetailResult(FirstIdParam param) {
        R<FirstDetailResult> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        FirstDetailResult result = firstRepository.getFirstDetailResult(param);
        r.setData(result);
        return r;
    }

    @Override
    public R<String> update(FirstUpdateParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        Integer id = param.getId();
        First first = firstRepository.selectById(id);
        if (CommonUtil.isEmpty(first)) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }

        First firstUpdate = param.toFirst();
        firstRepository.updateById(firstUpdate);

        return r;
    }
                                                
    @Override
    public R<String> deleteByIds(FirstIdsParam param) {
        R<String> r = new R<>();
        String checkParam = param.checkParam();
        if (!CommonUtil.isEmpty(checkParam)) {
            r.setSuccess(false);
            r.setErrMsg(checkParam);
            return r;
        }
        int result = firstRepository.deleteBatchIds(param.getIds());
        if (0 == result) {
            r.setSuccess(false);
            r.setErrMsg("数据不存在");
            return r;
        }
        return r;
    }
}
