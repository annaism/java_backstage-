package com.joker.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/08/20:25
 * @Description:
 */
@Data
public class PicUrl implements Serializable {

    private String url;
    private String name;
}
