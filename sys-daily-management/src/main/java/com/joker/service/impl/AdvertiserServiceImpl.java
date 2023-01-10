package com.joker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joker.entity.Advertiser;
import com.joker.mapper.AdvertiserMapper;
import com.joker.service.AdvertiserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告信息表 服务实现类
 * </p>
 *
 * @author Joker
 * @since 2022-12-04
 */
@Service
public class AdvertiserServiceImpl extends ServiceImpl<AdvertiserMapper, Advertiser> implements AdvertiserService {

}
