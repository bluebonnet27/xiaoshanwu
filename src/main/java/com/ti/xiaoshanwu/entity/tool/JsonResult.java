package com.ti.xiaoshanwu.entity.tool;

import com.alibaba.fastjson.JSONObject;

public class JsonResult<T> {
    //true 则本次请求成功，返回的数据在data中 false本次请求失败，错误信息记录在errMsg中
    private Boolean result;
    private String errMsg;
    private String resMsg;
    private T data;

    public JsonResult() {
    }

    public JsonResult(Boolean result, String errMsg, String resMsg) {
        this.result = result;
        this.errMsg = errMsg;
        this.resMsg = resMsg;
    }

    public JsonResult(Boolean result, T data) {
        this.result = result;
        this.data = data;
    }

    public JsonResult(Boolean result, String errMsg) {
        this.result = result;
        this.errMsg = errMsg;
    }


    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
