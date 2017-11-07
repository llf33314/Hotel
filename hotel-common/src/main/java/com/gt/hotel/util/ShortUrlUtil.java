package com.gt.hotel.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gt.api.util.JsonUtils;
import com.gt.api.util.MD5Utils;

/**
 * 短链接 工具类
 * @author Reverien9@gmail.com
 * 2017年11月7日 上午10:17:58
 */
@Component
public class ShortUrlUtil {
	
	private Logger logger = Logger.getLogger(ShortUrlUtil.class);
	
	@Value("${wxmp.short.url.path}")
	private String shortUrlPath;
	
	@Value("${wxmp.short.url.signkey}")
	private String signkey;
	
	public String getShorUrl(String longUrl) {
		try {
			// url路径
			String url = shortUrlPath + "/service/rest/shortUrl/getShortUrl?longUrl=" + longUrl;
			// 请求参数（长连接）
			String res = HttpKit.sendPostByHeaders(url, this.encryptHeader(), longUrl);
			logger.info("短连接接口返回数据:" + res);
			Map<String, Object> map = JsonUtils.json2Map(res);
			String code = map.get("code").toString();
			if ("1".equals(code)) {
				return (String) map.get("url");
			} else {
				return exGetShorUrl(longUrl);
			}
		} catch (Exception e) {
			logger.error("短连接生成失败!原因:" + e);
			throw new RuntimeException("短连接生成失败:" + e.getMessage());
		}
//			return longUrl;
	}
	
	public String exGetShorUrl(String longUrl) {
		int i = 100;
		// url路径
		String url = shortUrlPath + "/service/rest/shortUrl/getShortUrl";
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
		String key = MD5Utils.getMD5SM(signkey + timestamp);
		// 组合签名请求
		Map<String, String> headers = new HashMap<>();
		headers.put("key", key);
		headers.put("timestamp", timestamp);
		return headers;
	}
	
}
