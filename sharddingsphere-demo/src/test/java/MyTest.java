import com.den.sdsdemo.SdsApplication;
import com.den.sdsdemo.entity.Table3;
import com.den.sdsdemo.service.ITable1Service;
import com.den.sdsdemo.service.ITable2Service;
import com.den.sdsdemo.service.ITable3Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fatKarin
 * @date 2020/2/27 16:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SdsApplication.class)
public class MyTest {
    @Resource
    private ITable1Service table1Service;

    @Resource
    private ITable2Service table2Service;

    @Resource
    private ITable3Service table3Service;

    @Test
    public void table3Add() {
        Table3 table3 = new Table3();
        table3.setColumn2("default");
        table3.setColumn3("default");
        table3.setColumn4("default");
        table3.setColumn5("default");
        table3.setColumn6("default");
        table3.setCreateTime(new Date());

        table3Service.batchAdd();
    }

}