package com.gt.hotel.util;

import com.gt.api.util.JsonUtils;
import com.gt.api.util.MD5Utils;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 短链接 工具类
 *
 * @author Reverien9@gmail.com
 * 2017年11月7日 上午10:17:58
 */
@Slf4j
@Component
public class ShortUrlUtil {

    @Autowired
    private WebServerConfigurationProperties webServerConfigurationProperties;

    public String getShorUrl(String longUrl) {
        try {
            // url路径
            String url = webServerConfigurationProperties.getShortService().getApiMap().get("shortURL") + longUrl;
            // 请求参数（长连接）
            String res = HttpKit.sendPostByHeaders(url, this.encryptHeader(), longUrl);
            log.info("短连接接口返回数据: {}", res);
            Map<String, Object> map = JsonUtils.json2Map(res);
            if (MapUtils.isNotEmpty(map)) {
                if (1 == MapUtils.getIntValue(map, "code")) {
                    return (String) map.get("url");
                } else {
                    return exGetShorUrl(longUrl);
                }
            } else {
                log.warn(" map is null ");
                throw new NullPointerException("短连接服务无响应");
            }
        } catch (Exception e) {
            log.error("短连接生成失败!原因:", e);
            throw new RuntimeException("短连接生成失败:" + e.getMessage());
        }
    }

    public String exGetShorUrl(String longUrl) {
        int i = 100;
        // url路径
        String url = webServerConfigurationProperties.getShortService().getApiMap().get("getShortURL");
        String shorUrl = null;
        for (int j = 0; j < i; j++) {
            String res = HttpKit.sendPostByHeaders(url, this.encryptHeader(), longUrl);
            Map<String, Object> map = JsonUtils.json2Map(res);
            String code = map.get("code").toString();
            if ("1".equals(code)) {
                shorUrl = (String) map.get("url");
                break;
            }
        }
        if (shorUrl != null) {
            return shorUrl;
        } else {
            return longUrl;
        }
    }


    /**
     * 生成头部加密参数
     *
     * @return
     */
    private Map<String, String> encryptHeader() {
        // 生成时间戳
        String timestamp = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss");
        // 生成签名
        String key = MD5Utils.getMD5SM(webServerConfigurationProperties.getShortService().getSignKey() + timestamp);
        // 组合签名请求
        Map<String, String> headers = new HashMap<>();
        headers.put("key", key);
        headers.put("timestamp", timestamp);
        return headers;
    }

}
