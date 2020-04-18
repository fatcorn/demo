package com.den.sdsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.den.sdsdemo.Vo.Table1Vo;
import com.den.sdsdemo.entity.Table1;
import com.den.sdsdemo.util.IMyPage;
import com.den.sdsdemo.util.MyPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Table1Mapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2020-02-27
 */
public interface Table1Mapper extends BaseMapper<Table1> {


    IPage<Table1Vo> getTable1Vo(@Param("page") Page page, @Param("t1Id") Long t1Id);

}
