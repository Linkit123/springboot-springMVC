/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng;

/**
 *
 * @author linhtn
 */
public class CustomPaging {
    
    private int pageIndex;
    private int pageSize;
    private int recordCount;
    private int pageTotal;
    private int recordsTotal;

    public CustomPaging() {
    }

    public CustomPaging(int pageIndex, int pageSize, int recordCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
    }
    
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
    
//    public int getTotalPage() {
//        if (recordsTotal % pageSize > 0) {
//            pageTotal = (recordsTotal / pageSize + 1);
//
//        } else {
//            pageTotal = recordsTotal / pageSize;
//        }
//        if (pageTotal <= 0) {
//            pageTotal = 1;
//        }
//        return pageTotal;
//    }
    
}
