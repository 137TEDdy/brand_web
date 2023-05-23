package com.itheima.pojo;

import java.util.List;

public class PageBean<T> {
    int totalCount; //�ܼ�¼��
    List<T> rows;  //��ѯ�Ľ��

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
