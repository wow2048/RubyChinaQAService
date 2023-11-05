package com.example.RubyChinaQAService.entity;

import lombok.Data;

@Data
public class OperationRst {
    private String status;

    private String msg;

    private Object result;

    public static OperationRst buildSuccess(Object result) {
        OperationRst operationRst = new OperationRst();
        operationRst.setStatus(Const.SUCCESS);
        operationRst.setResult(result);
        return operationRst;
    }

    public static OperationRst buildFail(String msg) {
        OperationRst operationRst = new OperationRst();
        operationRst.setStatus(Const.FAIL);
        operationRst.setMsg(msg);
        return operationRst;
    }
}
