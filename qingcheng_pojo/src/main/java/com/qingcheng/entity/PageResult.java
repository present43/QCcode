package com.qingcheng.entity;

import java.io.Serializable;
import java.util.List;

//自己封装的实体类 用来做分页
public class PageResult<T> implements Serializable {
    //    总记录数
    private Long total;
    //    行数
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
