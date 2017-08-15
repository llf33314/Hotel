package com.gt.hotel.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.BusUserDAO;
import com.gt.hotel.entity.BusUser;
import com.gt.hotel.exception.BusinessException;
import com.gt.hotel.web.service.BusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangmz
 * @since 2017-07-10
 */
@Service( "webBusService" )
public class BusUserServiceImpl extends BaseServiceImpl< BusUserDAO,BusUser > implements BusUserService {
    
	@Autowired
    private BusUserDAO busUserDAO;

    @Override
    public BusUser findUser( final Integer uid ) {
	return this.busUserDAO.selectOne( new BusUser() {{
	    setId( uid );
	}} );
    }

    @Override
    public List< BusUser > findUsername( String username ) {
	if ( username == null ) {
	    throw new BusinessException( "参数错误" );
	}
	Wrapper< BusUser > busUserWrapper = new EntityWrapper<>();
	busUserWrapper.like( "username", username );

	List< BusUser > userList = this.busUserDAO.selectList( busUserWrapper );
	return userList;
    }
}
