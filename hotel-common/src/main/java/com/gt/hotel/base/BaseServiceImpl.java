package com.gt.hotel.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * 公共实现类
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/08/03
 * @since 1.0.0
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

}
