package com.gt.hotel.web.service.impl;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TActivityDAO;
import com.gt.hotel.entity.TActivity;
import com.gt.hotel.web.service.TActivityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动设置 - 抽出活动公共设置 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class TActivityServiceImpl extends BaseServiceImpl< TActivityDAO, TActivity > implements TActivityService {

}
