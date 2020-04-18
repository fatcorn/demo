package com.den.sdsdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.sdsdemo.entity.Table2;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2020-02-27
 */
public interface ITable2Service extends IService<Table2> {

    void batchAdd();
}
