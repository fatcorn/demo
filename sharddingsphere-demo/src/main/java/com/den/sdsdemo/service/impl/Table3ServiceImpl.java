package com.den.sdsdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.sdsdemo.entity.Table3;
import com.den.sdsdemo.mapper.Table3Mapper;
import com.den.sdsdemo.service.ITable3Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fatKarin
 * @since 2020-02-27
 */
@Service
public class Table3ServiceImpl extends ServiceImpl<Table3Mapper, Table3> implements ITable3Service {

    @Override
    @Async
    public void batchAdd() {
        Table3 table3 = new Table3();
        table3.setColumn2("default");
        table3.setColumn3("default");
        table3.setColumn4("default");
        table3.setColumn5("default");
        table3.setColumn6("default");
        table3.setCreateTime(new Date());

        ExecutorService executorService = Executors.newFixedThreadPool(30);

        Thread task = new Thread(() ->{
            int i = 10000;
            while ( i-- > 0) {
                 this.save(table3);
                 table3.setId(null);
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
