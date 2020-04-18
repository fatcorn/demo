package com.den.sdsdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.sdsdemo.entity.Table2;
import com.den.sdsdemo.mapper.Table2Mapper;
import com.den.sdsdemo.service.ITable2Service;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fatKarin
 * @since 2020-02-27
 */
@Service
public class Table2ServiceImpl extends ServiceImpl<Table2Mapper, Table2> implements ITable2Service {

    @Override
    public void batchAdd() {

        Table2 table2 = new Table2();
        table2.setColumn2("default");
        table2.setColumn3("default");
        table2.setColumn4("default");
        table2.setColumn5("default");
        table2.setColumn6("default");
        table2.setCreateTime(new Date());

        ExecutorService executorService = Executors.newFixedThreadPool(30);

        Thread task = new Thread(() ->{
            int i = 10000;
            while ( i-- > 0) {
                this.save(table2);
                table2.setId(null);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i = 0; i < 30; i++) {
            executorService.submit(task);
        }
    }
}
