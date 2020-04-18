package com.den.sdsdemo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.sdsdemo.Vo.Table1Vo;
import com.den.sdsdemo.entity.Table1;
import com.den.sdsdemo.entity.Table2;
import com.den.sdsdemo.mapper.Table1Mapper;
import com.den.sdsdemo.service.ITable1Service;
import com.den.sdsdemo.service.ITable2Service;
import com.den.sdsdemo.service.ITable3Service;
import com.den.sdsdemo.util.IMyPage;
import com.den.sdsdemo.util.MyPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fatKarin
 * @since 2020-02-27
 */
@Service
public class Table1ServiceImpl extends ServiceImpl<Table1Mapper, Table1> implements ITable1Service {

    @Override
    public void batchAdd() {
        Table1 table1 = new Table1();
        table1.setColumn2("default");
        table1.setColumn3("default");
        table1.setColumn4("default");
        table1.setColumn5("default");
        table1.setColumn6("default");
        table1.setCreateTime(new Date());

        new Thread(() ->{
            int i = 300000;
            while ( i-- > 0) {
                int randomTable2 = new Random().nextInt(300001);
                int randomTable3 = new Random().nextInt(300001);
                table1.setTable2Id((long) randomTable2);
                table1.setTable3Id((long) randomTable3);
                this.save(table1);
                table1.setId(null);
            }
        }).start();


    }

    @Override
    public IPage<Table1Vo> getTable1Vo(Long t1Id, Integer currentPage, Integer pageSize) {
        Page<Table1Vo> page = new Page<>(currentPage,pageSize);
        return this.baseMapper.getTable1Vo(page,t1Id);
    }
}
