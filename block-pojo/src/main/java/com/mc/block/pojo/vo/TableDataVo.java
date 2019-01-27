package com.mc.block.pojo.vo;

import com.mc.block.pojo.page.Page;

import java.io.Serializable;
import java.util.List;

public class TableDataVo implements Serializable {
    private List<?> tableData;
    private Page page;

    public List<?> getTableData() {
        return tableData;
    }

    public TableDataVo setTableData(List<?> tableData) {
        this.tableData = tableData;
        return this;
    }

    public Page getPage() {
        return page;
    }

    public TableDataVo setPage(Page page) {
        this.page = page;
        return this;
    }
}
