package com.gt.hotel.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/09
 * @since
 */
@Data
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

        private Map<String,String> ApiMap;
    }

    @Data
    @Component
    public static class ShortService {
        private String signKey;

        private String serviceUrl;

        private Map<String,String> ApiMap;
    }

    @Data
    @Component
    public static class MemberService {
        private String signKey;

        private String serviceUrl;

        private Map<String,String> ApiMap;
    }
    
    @Data
    @Component
    public static class JxcService {
    	
    	private String serviceUrl;

    	private String account;
    	
    	private String pwd;
    	
    	private Map<String,String> ApiMap;
    }

}
