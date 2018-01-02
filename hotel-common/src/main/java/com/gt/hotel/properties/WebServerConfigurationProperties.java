package com.gt.hotel.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/09
 * @since
 */
@Component
@ConfigurationProperties(prefix = "hotel.webserver")
public class WebServerConfigurationProperties {

    private WxmpService wxmpService;

    private ShortService shortService;

    private MemberService memberService;

    private JxcService jxcService;


    @Data
    @Component
    public static class WxmpService {
        private String signKey;

        private String serviceUrl;

        private String imageUrl;
        
        private String materialUrl;
        
        private String socketUrl;
        
        private String wxmpLogin;
        
        private String alipayRefundUrl;
        
        private Map<String,String> apiMap;
    }

    @Data
    @Component
    public static class ShortService {
        private String signKey;

        private String serviceUrl;

        private Map<String,String> apiMap;
    }

    @Data
    @Component
    public static class MemberService {
        private String signKey;

        private String serviceUrl;

        private Map<String,String> apiMap;
    }
    
    @Data
    @Component
    public static class JxcService {
    	
    	private String serviceUrl;

    	private String account;
    	
    	private String pwd;
    	
    	private Map<String,String> apiMap;
    }


    public WxmpService getWxmpService() {
        return wxmpService;
    }

    public void setWxmpService(WxmpService wxmpService) {
        this.wxmpService = wxmpService;
    }

    public ShortService getShortService() {
        return shortService;
    }

    public void setShortService(ShortService shortService) {
        this.shortService = shortService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public JxcService getJxcService() {
        return jxcService;
    }

    public void setJxcService(JxcService jxcService) {
        this.jxcService = jxcService;
    }
}
