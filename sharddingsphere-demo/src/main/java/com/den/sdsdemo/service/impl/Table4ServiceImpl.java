package com.den.sdsdemo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.sdsdemo.Vo.Table1Vo;
import com.den.sdsdemo.Vo.Table4Vo;
import com.den.sdsdemo.entity.Table1;
import com.den.sdsdemo.entity.Table4;
import com.den.sdsdemo.mapper.Table4Mapper;
import com.den.sdsdemo.service.ITable4Service;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * @author fatKarin
 * @since 2020-04-17
 */
@Service
public class Table4ServiceImpl extends ServiceImpl<Table4Mapper, Table4> implements ITable4Service {
    @Override
    public void batchAdd() {
        Table4 table4 = new Table4();
        table4.setColumn2("default");
        table4.setColumn3("default");
        table4.setColumn4("default");
        table4.setColumn5("default");
        table4.setColumn6("default");
        table4.setCreateTime(new Date());

        new Thread(() -> {
            int i = 300000;
            while (i-- > 0) {
                int randomTable2 = new Random().nextInt(300001);
                int randomTable3 = new Random().nextInt(300001);
                table4.setTable2Id((long) randomTable2);
                table4.setTable3Id((long) randomTable3);
                this.save(table4);
            }
        }).start();

    }

    @Override
    public IPage<Table4Vo> getTable4Vo(Long t4Id, Integer currentPage, Integer pageSize) {
        Page<Table1Vo> page = new Page<>(currentPage,pageSize);
        return this.baseMapper.findByPage(page,t4Id);
    }
}
