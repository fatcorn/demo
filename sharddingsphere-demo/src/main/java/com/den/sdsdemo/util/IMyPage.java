package com.den.sdsdemo.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author fatKarin
 * @date 2020/4/17 15:16
 */
public interface IMyPage<T> extends Serializable {
    default String[] descs() {
        return null;
    }

    default String[] ascs() {
        return null;
    }

    default Map<Object, Object> condition() {
        return null;
    }

    default boolean optimizeCountSql() {
        return true;
    }

    default boolean isSearchCount() {
        return true;
    }

    default long offset() {
        return this.getCurrent() > 0L ? (this.getCurrent() - 1L) * this.getSize() : 0L;
    }

    default long getPages() {
        if (this.getSize() == 0L) {
            return 0L;
        } else {
            long pages = this.getTotal() / this.getSize();
            if (this.getTotal() % this.getSize() != 0L) {
                ++pages;
            }

            return pages;
        }
    }

    default IMyPage<T> setPages(long pages) {
        return this;
    }

    List<T> getRecords();

    IMyPage<T> setRecords(List<T> records);

    long getTotal();

    IMyPage<T> setTotal(long total);

    long getSize();

    IMyPage<T> setSize(Integer size);

    long getCurrent();

    IMyPage<T> setCurrent(Integer current);

    default <R> IMyPage convert(Function<? super T, ? extends R> mapper) {
        List collect = (List)this.getRecords().stream().map(mapper).collect(Collectors.toList());
        return this.setRecords(collect);
    }
}