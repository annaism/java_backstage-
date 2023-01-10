package com.joker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joker.entity.Advertiser;
import com.joker.interceptor.LoginUserInterceptor;
import com.joker.service.AdvertiserService;
import com.joker.utils.R;
import com.joker.vo.AdvertiserSearch;
import com.joker.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
public class AdvertiserController {

    @Autowired
    AdvertiserService advertiserService;

    //添加公告信息

    @RequestMapping("add")
    public R add(@RequestBody Advertiser vo) throws ParseException {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = formatter.parse(formatter.format(date));
        java.sql.Date sql_date = new java.sql.Date(parse.getTime());

        log.info(sql_date.toString());
        vo.setDate(sql_date);

        UserVo userVo = LoginUserInterceptor.userMainData.get();
        vo.setPubliser(userVo.getName());

        boolean save = advertiserService.save(vo);
        if( save ){
            return R.ok().data("msg","添加公告成功");
        }

        return R.error().data("msg","添加公告失败");
    }

    //条件查询公告信息 --- test
    @RequestMapping("list")
    public R list(@RequestBody AdvertiserSearch vo){

        log.info(vo.toString());
        //封装查询条件
        QueryWrapper<Advertiser> wrapper = new QueryWrapper<>();

        if( vo.getPage() == null || vo.getPage() < 1){
            vo.setPage(Long.valueOf(1));
        }
        if( vo.getLimit() == null || vo.getLimit() < 5){
            vo.setLimit(Long.valueOf(5));
        }

        //创建page对象
        Page<Advertiser> page = new Page<>(vo.getPage(),vo.getLimit());

        if( !StringUtils.isEmpty(vo.getBegin())){
            wrapper.ge("date",vo.getBegin());
        }
        if( !StringUtils.isEmpty(vo.getEnd())){
            wrapper.le("date",vo.getEnd());
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
    public R delete(@RequestBody List<Long> Ids){

        log.info("{}",Ids);

        if(Ids == null || Ids.size() == 0){
            return  R.error().data("msg","删除失败,请重试");
        }

        boolean key = advertiserService.removeByIds(Ids);

        if(key){
            return R.ok().data("msg","删除成功");
        }
        return R.error().data("msg","删除失败,请重试");
    }

}

