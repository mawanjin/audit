package com.ljs.bo;

import java.util.List;

/**
 * Created by lala on 16/8/22.
 */
public class Page<T> {
    /** 第几页*/
    long pageNO;
    /** 第页条数*/
    int pageSize = 10;
    /** 总条数*/
    long totalCount;
    /** 总页数 */
    long totalPage;

    boolean hasNxt;
    boolean hasPre;

    List<T> list;

    public long getPageNO() {
        return pageNO;
    }

    public void setPageNO(long pageNO) {
        this.pageNO = pageNO;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
         return (totalCount%pageSize==0)?totalCount/pageSize:totalCount/pageSize+1;
//        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isHasNxt() {
        if(pageNO==getTotalPage()||getTotalPage()<=1)return false;
        return true;
    }

    public void setHasNxt(boolean hasNxt) {
        this.hasNxt = hasNxt;
    }

    public boolean isHasPre() {
        if(pageNO==1||getTotalPage()==0)return false;
        return true;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }
}
