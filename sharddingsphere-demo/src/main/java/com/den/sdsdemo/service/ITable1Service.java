package com.den.sdsdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.den.sdsdemo.Vo.Table1Vo;
import com.den.sdsdemo.entity.Table1;
import com.den.sdsdemo.util.IMyPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2020-02-27
 */
public interface ITable1Service extends IService<Table1> {
    void batchAdd();

    IPage<Table1Vo> getTable1Vo(Long t1Id, Integer currentPage, Integer pageSize);
}
