package com.joker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joker.entity.Advertiser;
import com.joker.interceptor.LoginAndRefreshTokenInterceptor;
import com.joker.service.AdvertiserService;
import com.joker.utils.R;
import com.joker.vo.AdvertiserSearch;
import com.joker.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.joker.constant.Constant.ADD_SUCCESS;
import static com.joker.constant.Constant.DEL_SUCCESS;

/**
 * <p>
 * 公告信息表 前端控制器
 * </p>
 *
 * @author Joker
 * @since 2022-12-04
 */
@RestController
@RequestMapping("/api/daily/advertiser")
@Slf4j
@Validated
@ControllerAdvice
public class AdvertiserController {

    @Autowired
    AdvertiserService advertiserService;

    //添加公告信息
    @RequestMapping("add")
    public R add(@RequestBody @Valid Advertiser vo) throws ParseException {

        UserVo userVo = LoginAndRefreshTokenInterceptor.userMainData.get();
        vo.setPubliser(userVo.getName());

        vo.setDate(new Date());
        advertiserService.save(vo);

        R ok = R.ok();
        ok.setMessage( ADD_SUCCESS.getMsg() );
        return ok;
    }

    //条件查询公告信息
    @RequestMapping("list")
    public R list(@RequestBody @Valid AdvertiserSearch vo){

        log.info(vo.toString());
        //封装查询条件
        QueryWrapper<Advertiser> wrapper = new QueryWrapper<>();


        //创建page对象
        Page<Advertiser> page = new Page<>(vo.getPage(),vo.getLimit());

        if( vo.getDate() != null && vo.getDate().size() >= 2){
            wrapper.ge("date",vo.getDate().get(0));
        }
        if( vo.getDate() != null && vo.getDate().size() >= 2){
            wrapper.le("date",vo.getDate().get(1));
        }

        Page<Advertiser> res = advertiserService.page(page, wrapper);

        long total = res.getTotal();
        List<Advertiser> records = res.getRecords();

        HashMap<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    //删除公告信息
    @RequestMapping("del")
    public R delete(@RequestBody @NotEmpty(message = "请选择需要删除的数据") List<Long> Ids){

        log.info("{}",Ids);

        advertiserService.removeByIds(Ids);

        R ok = R.ok();
        ok.setMessage( DEL_SUCCESS.getMsg() );
        return ok;
    }

}

