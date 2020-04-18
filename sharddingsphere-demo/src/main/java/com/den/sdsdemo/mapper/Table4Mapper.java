package com.den.sdsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.den.sdsdemo.Vo.Table4Vo;
import com.den.sdsdemo.entity.Table4;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Table4Mapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2020-04-17
 */

public interface Table4Mapper extends BaseMapper<Table4> {

    IPage<Table4Vo> findByPage(@Param("page") Page page, @Param("t4Id") Long t4Id);
}
