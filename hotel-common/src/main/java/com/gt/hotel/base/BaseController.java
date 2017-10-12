package com.gt.hotel.base;

import com.gt.hotel.exception.ResponseEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * BaseController
 *
 * @author zhangmz
 * @create 2017/7/10
 */
public abstract class BaseController {
    /**
     * 日志
     */
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 获取Sessionid
     *
     * @param session HttpSession
     *
     * @return
     */
    public String getSessionId(HttpSession session) {
	return session.getId();
    }

    /**
     * 暂时写死一个 id
     * TODO: 待完善 登录流程
     *
     * @param session HttpSession
     *
     * @return int
     */
    public Integer getLoginUserId(HttpSession session) {
	//       Object o = session.getAttribute(CommonSessionConst.CURRENT_BUS_USER);
	return 33;
    }

    /**
     * 参数校验
     *
     * @param result BindingResult
     */
    protected void InvalidParameter(BindingResult result) {
	if (result.hasErrors()) {
	    List< ObjectError > errorList = result.getAllErrors();
	    for (ObjectError error : errorList) {
		logger.warn(error.getDefaultMessage());
		throw new ResponseEntityException(error.getDefaultMessage());
	    }
	}
    }

}
