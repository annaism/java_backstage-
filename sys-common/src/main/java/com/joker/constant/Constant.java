package com.joker.constant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/22/17:33
 * @Description:
 */
public enum Constant {
    NOT_PHONENUM("手机号码格式不正确"),
    PHONENUM_ALREADY_IN("手机号码已存在"),

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

    COMMENT_SUCCES("评论成功"),
    COMMENT_FAILD("评论失败"),

    ORDER_SUCCES("下单成功"),
    ORDER_FAILD("下单失败"),

    STATUS_SUCCES("更新状态成功"),
    STATUS_FAILD("更新状态失败"),

    REDIS_TOKEN_CACHE_PREFIX("TOKEN:"),
    REDIS_PER_PREFIX("ACLPERPREFIX"),

    ORDER_CANCLE("订单已取消"),
    ORDER_UPDATE_SUCCES("订单信息修改成功"),

    TO_SUCCESS("处理成功"),
    TO_FAILD("处理失败"),

    ASSIGN_FAILD("分配失败"),
    ASSIGN_SUCCESS("分配成功"),

    INF_MENU_PRE_PREFIX("INFO:MENU:"),
    ROLES_PRE_PREFIX("ROLE:"),
    TOKEN_PRE_PREFIX("TOKEN:"),
    PERSSION_PRE_PREFIX("PERSSION:");


    public String getMsg() {
        return msg;
    }

    ;





    public String msg;

    Constant(String msg) {
        this.msg = msg;
    }

}
