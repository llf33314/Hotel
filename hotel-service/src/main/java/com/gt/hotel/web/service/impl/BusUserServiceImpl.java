package com.gt.hotel.web.service.impl;

import com.gt.hotel.web.service.BusUserService;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.BusUserDAO;
import com.gt.hotel.entity.BusUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangmz
 * @since 2017-07-10
 */
@Service("webBusService")
public class BusUserServiceImpl extends BaseServiceImpl< BusUserDAO, BusUser > implements BusUserService {
    @Autowired
    private BusUserDAO busUserDAO;

    @Override
    public BusUser findUser( final Integer uid ) {
        return this.busUserDAO.selectOne( new BusUser() {{
            setId( uid );
        }} );
    }
}
