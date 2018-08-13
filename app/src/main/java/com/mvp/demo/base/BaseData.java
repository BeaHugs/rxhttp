package com.mvp.demo.base;

/**
 * @author Wang Yi Bo
 * @date 2018/5/24
 * 作用:
 */

public class BaseData<T> {
    public String ReturnStatus;//0 成功  1 失败  2 异常
    public String ReturnReason;//失败的原因
    public T ReturnMessage;
    public String Remarks;// 成功  失败
    public String Total;  // 失败和异常为0  成功为成功的条数
}
