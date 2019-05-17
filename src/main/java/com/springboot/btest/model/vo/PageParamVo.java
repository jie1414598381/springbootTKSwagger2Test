package com.springboot.btest.model.vo;


import static com.springboot.btest.common.Const.DEFAULT_PAGENUM;
import static com.springboot.btest.common.Const.DEFAULT_PAGESIZE;

public class PageParamVo {

    private int startPage;
    private int pageSize;
    public PageParamVo(int startPage, int pageSize) {
        setStartPage(startPage);
        setPageSize(pageSize);
    }

    public PageParamVo() {
    }

    public int getStartPage() {
        return startPage <= 0?DEFAULT_PAGENUM:startPage;
    }

    public void setStartPage(int startPage) {
        if (startPage <= 0)
            this.startPage = DEFAULT_PAGENUM;
        else
            this.startPage = startPage;
    }

    public int getPageSize() {
        return pageSize <= 0?DEFAULT_PAGESIZE:pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0)
            this.pageSize = DEFAULT_PAGESIZE;
        else
            this.pageSize = pageSize;
    }
    public int getStartIndex(){
        return (this.startPage -1) * this.pageSize;
    }
    public int getEndIndex(){
        return getStartPage() * getPageSize() - 1;
    }
}
