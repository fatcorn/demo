package com.den.sdsdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * (Table1)实体类
 *
 * @author makejava
 * @since 2020-02-27 16:17:40
 */
@TableName("table_4")
public class Table4 implements Serializable {
    private static final long serialVersionUID = 957380781884281067L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    
    private String column2;
    
    private String column3;
    
    private Long table2Id;
    
    private String column4;
    
    private String column5;
    
    private String column6;
    
    private Date createTime;
    
    private Long table3Id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public Long getTable2Id() {
        return table2Id;
    }

    public void setTable2Id(Long table2Id) {
        this.table2Id = table2Id;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getColumn6() {
        return column6;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getTable3Id() {
        return table3Id;
    }

    public void setTable3Id(Long table3Id) {
        this.table3Id = table3Id;
    }

}