package com.joker.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/04/17:20
 * @Description:
 */
@Data
public class PermissionVo implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(1:菜单,2:按钮)
     */
    private Integer type;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 图标
     */
    private String icon;
}
