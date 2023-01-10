package com.joker.constant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/22/17:33
 * @Description:
 */
public enum Constant {

    DATA_NOT_EXITS("数据不存在，请检查后重试"),
    ADD_SUCCESS("添加成功"),
    ADD_FAILD("添加失败"),
    UPDATE_SUCCESS("修改成功"),
    UPDATE_FAILD("修改失败"),
    DEL_SUCCESS("删除成功"),
    DEL_FAILD("删除失败"),
    REQUISITION_SUCCESS("申请成功"),
    REQUISITION_FAILD("申请失败"),
    CHECK_SUCCESS("审核成功"),
    CHECK_FAILD("审核失败"),
    CANCEL_SUCCES("取消成功"),
    CANCEL_FAILD("取消失败"),




    REDIS_TOKEN_CACHE_PREFIX("TOKEN:");








    ;





    private String msg;

    Constant(String msg) {
        this.msg = msg;
    }

}
