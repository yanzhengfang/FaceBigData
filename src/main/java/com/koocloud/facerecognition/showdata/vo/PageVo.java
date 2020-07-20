package com.koocloud.facerecognition.showdata.vo;

/**
 * @author anna
 * @create 2020-07-18 22:30
 */
public class PageVo {
    private  Integer page =1;// 第几页

    private  Integer start =0;//起始条数

    private  Integer limit=15;//每页多少条

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
