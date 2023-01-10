package com.joker.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/01/19:50
 * @Description: 封装User前端交互类
 */
@Data
@ToString
@Getter
@Setter
public class UserReturnVo implements Serializable {

    private Long id;
    private String name;
    private Integer sex;
    private Integer age;
    private String phone;
    private String address;
    private String cardId;

    public UserReturnVo() {
    }

    public UserReturnVo(Long id, String name,Integer sex, Integer age, String phone, String address, String cardId) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.cardId = cardId;
    }
}
