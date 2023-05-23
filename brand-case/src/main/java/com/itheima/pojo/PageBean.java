package com.itheima.pojo;

import java.util.List;

public class PageBean<T> {
    int totalCount; //总记录数
    List<T> rows;  //查询的结果

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
