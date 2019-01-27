package com.mc.block.pojo.page;

import java.io.Serializable;

public class Page implements Serializable {
    private Long total;
    private Integer currentPage = 1;
    private Integer pageSize = 10;

    public Long getTotal() {
        return total;
    }

    public Page setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Page setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Page setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
