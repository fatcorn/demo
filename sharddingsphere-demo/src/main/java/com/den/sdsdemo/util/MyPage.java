package com.den.sdsdemo.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author fatKarin
 * @date 2020/2/28 16:28
 */
public class MyPage<T> implements IMyPage<T> {

    private static final long serialVersionUID = 8545996863226528798L;
    private List<T> records;
    private Integer total;
    private Integer size;
    private Integer current;
    private String[] ascs;
    private String[] descs;
    private boolean optimizeCountSql;
    private boolean isSearchCount;

    public MyPage() {
        this.records = Collections.emptyList();
        this.total = 0;
        this.size = 10;
        this.current = 1;
        this.optimizeCountSql = true;
        this.isSearchCount = true;
    }

    public MyPage(Integer current, Integer size) {
        this(current, size, 0);
    }

    public MyPage(Integer current, Integer size, Integer total) {
        this(current, size, total, true);
    }

    public MyPage(Integer current, Integer size, boolean isSearchCount) {
        this(current, size, 0, isSearchCount);
    }

    public MyPage(Integer current, Integer size, Integer total, boolean isSearchCount) {
        this.records = Collections.emptyList();
        this.total = 0;
        this.size = 10;
        this.current = 1;
        this.optimizeCountSql = true;
        this.isSearchCount = true;
        if (current > 1L) {
            this.current = current;
        }

        this.size = size;
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    public boolean hasPrevious() {
        return this.current > 1L;
    }

    public boolean hasNext() {
        return this.current < this.getPages();
    }

    public List<T> getRecords() {
        return this.records;
    }

    public MyPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public long getTotal() {
        return this.total;
    }

    @Override
    public IMyPage<T> setTotal(long total) {
        this.total = (int) total;
        return this;
    }

    public MyPage<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public long getSize() {
        return this.size;
    }

    @Override
    public IMyPage<T> setSize(Integer size) {
        this.size = (int) total;
        return this;
    }


    public long getCurrent() {
        return this.current;
    }

    @Override
    public IMyPage<T> setCurrent(Integer current) {
        return null;
    }


    public String[] ascs() {
        return this.ascs;
    }

    public MyPage<T> setAscs(List<String> ascs) {
        if (CollectionUtils.isNotEmpty(ascs)) {
            this.ascs = (String[])ascs.toArray(new String[0]);
        }

        return this;
    }

    public MyPage<T> setAsc(String... ascs) {
        this.ascs = ascs;
        return this;
    }

    public String[] descs() {
        return this.descs;
    }

    public MyPage<T> setDescs(List<String> descs) {
        if (CollectionUtils.isNotEmpty(descs)) {
            this.descs = (String[])descs.toArray(new String[0]);
        }

        return this;
    }

    public MyPage<T> setDesc(String... descs) {
        this.descs = descs;
        return this;
    }

    public boolean optimizeCountSql() {
        return this.optimizeCountSql;
    }

    public boolean isSearchCount() {
        return this.total >= 0L && this.isSearchCount;
    }

    public MyPage<T> setSearchCount(boolean isSearchCount) {
        this.isSearchCount = isSearchCount;
        return this;
    }

    public MyPage<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }
}
