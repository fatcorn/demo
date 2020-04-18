package com.den.sdsdemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.den.sdsdemo.Vo.Table1Vo;
import com.den.sdsdemo.Vo.Table4Vo;
import com.den.sdsdemo.entity.Table1;
import com.den.sdsdemo.service.ITable1Service;
import com.den.sdsdemo.service.ITable2Service;
import com.den.sdsdemo.service.ITable3Service;
import com.den.sdsdemo.service.ITable4Service;
import com.den.sdsdemo.util.IMyPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fatKarin
 * @date 2020/2/27 17:10
 */
@RestController
@RequestMapping("/my")
public class MyController {
    @Resource
    private ITable1Service table1Service;

    @Resource
    private ITable2Service table2Service;

    @Resource
    private ITable3Service table3Service;

    @Resource
    private ITable4Service table4Service;

    @GetMapping("/table3BatchAdd")
    public void table3BatchAdd() {
        table3Service.batchAdd();
    }

    @GetMapping("/table2BatchAdd")
    public void table2BatchAdd() {
        table2Service.batchAdd();
    }

    @GetMapping("/table1BatchAdd")
    public void table1BatchAdd() {
        table1Service.batchAdd();
    }

    @GetMapping("/table4BatchAdd")
    public void table4BatchAdd() {
        table4Service.batchAdd();
    }

    @PostMapping("/getTableVoList")
    public IPage<Table1Vo> getTableVoList(Long t1Id, Integer currentPage, Integer pageSize) {
        return table1Service.getTable1Vo(t1Id,currentPage,pageSize);
    }

    @PostMapping("/getTable4VoVoList")
    public IPage<Table4Vo> getTable4VoVoList(Long t1Id, Integer currentPage, Integer pageSize) {
        return table4Service.getTable4Vo(t1Id,currentPage,pageSize);
    }
}
