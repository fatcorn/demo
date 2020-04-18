package com.den.sdsdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.den.sdsdemo.Vo.Table4Vo;
import com.den.sdsdemo.entity.Table4;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2020-04-17
 */
public interface ITable4Service extends IService<Table4> {
    void batchAdd();

    IPage<Table4Vo> getTable4Vo(Long t1Id, Integer currentPage, Integer pageSize);
}
