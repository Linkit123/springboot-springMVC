/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng;

import java.util.*;

/**
 *
 * @author linhtn
 * This class custom response of retfulAPI
 */
public class CustomResponse {
    private String code;
    private String message;
    private Object data;
    private CustomPaging paging;

    public CustomResponse(String code, String message, Object data, CustomPaging paging) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.paging = paging;
    }

    public CustomResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CustomPaging getPaging() {
        return paging;
    }

    public void setPaging(CustomPaging paging) {
        this.paging = paging;
    }



}
