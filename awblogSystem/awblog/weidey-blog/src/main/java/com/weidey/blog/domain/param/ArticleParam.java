package com.weidey.blog.domain.param;

import cn.hutool.core.date.DateTime;
import lombok.Data;

public class ArticleParam {

    /**
     * 请求分页大小
     */
    private int  pageSize;

     /**
     * 请求分页页数
     */
     private int  pageNum;

    /**
     *  查询时间考前的
     */
     private DateTime dateBy;

     /**
     * 查询推荐等级靠前的
     */
     private int recommend;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public DateTime getDateBy() {
        return dateBy;
    }

    public void setDateBy(DateTime dateBy) {
        this.dateBy = dateBy;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }
}
