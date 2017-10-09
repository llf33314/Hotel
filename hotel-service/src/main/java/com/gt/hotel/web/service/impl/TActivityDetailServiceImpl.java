package com.gt.hotel.web.service.impl;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TActivityDetailDAO;
import com.gt.hotel.entity.TActivityDetail;
import com.gt.hotel.web.service.TActivityDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动详情表 根据活动类型不同整合各个活动所需字段 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class TActivityDetailServiceImpl extends BaseServiceImpl< TActivityDetailDAO, TActivityDetail > implements TActivityDetailService {

}
