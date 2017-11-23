package com.gt.hotel.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.gt.hotel.properties.WebServerConfigurationProperties; 

/**
 * 进销存接口
 * @author Reverien9@gmail.com
 * 2017年11月23日 下午4:35:42
 */
@SuppressWarnings("deprecation")
@Component
public class JXCApiUtil {
	
	private final static String CHARSET = "utf-8";  
	private final static int CODE_1 = 1001;	//调用成功并返回请求数据
	private final static int CODE_2 = 1002;	//异常(包括请求数据、系统运行异常等)
	private final static int CODE_3 = 1003;	//未登录状态，无权操作
	private final static int CODE_4 = 1004;	//TOKEN过期

    @Autowired
    private WebServerConfigurationProperties properties;
    
    /**
     * 进销存登录 返回token
     * @return
     */
    private String login() {
    	Map<String, String> map = new HashMap<>();
    	map.put("account", properties.getJxcService().getAccount());
    	map.put("pwd", properties.getJxcService().getPwd());
		String result = doPost(properties.getJxcService().getApiMap().get("login"), map , null);
		JSONObject json = JSONObject.parseObject(result);
		if(json.getIntValue("code") == CODE_1) {
			return json.getJSONObject("data").getString("token");
		}else {
			return null;
		}
    }
    
    /**
     * 查询已入库的商品信息
     * @param shopIds 门店ID 多个用","分割
     * @param proTypes 商品分类 多个用","分割
     * @param search 搜索条件 （商品名称or条形码or自定义编码）
     * @param pageIndex 页数
     * @param pageCount 条数
     * @return
     */
    public JSONObject allProducts(String shopIds, String proTypes, String search, String pageIndex, String pageCount) {
    	Map<String, String> map = new HashMap<>();
    	map.put("shopIds", shopIds);
    	map.put("proTypes", proTypes);
    	map.put("search", search);
    	map.put("pageIndex", pageIndex);
    	map.put("pageCount", pageCount);
		String token = null;//TODO 暂时……
		String result = doPost(properties.getJxcService().getApiMap().get("allProducts"), map , token);
    	JSONObject json = JSONObject.parseObject(result);
		return json;
    }
    
	public String doPost(String url, Map<String,String> map, String token){  
    	HttpClient httpClient = null;  
    	HttpPost httpPost = null;  
    	String result = null;  
    	try{  
    		httpClient = new SSLClient();  
    		httpPost = new HttpPost(url);
    		if(token != null) {
    			httpPost.setHeader("token", token);
    		}
    		List<NameValuePair> list = new ArrayList<NameValuePair>();  
    		for(Entry<String, String> elem : map.entrySet()){  
    			list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));  
    		}  
    		if(list.size() > 0){  
    			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, CHARSET);  
    			httpPost.setEntity(entity);  
    		}  
    		HttpResponse response = httpClient.execute(httpPost);  
    		if(response != null){  
    			HttpEntity resEntity = response.getEntity();  
    			if(resEntity != null){  
    				result = EntityUtils.toString(resEntity, CHARSET);  
    			}  
    		}  
    	}catch(Exception e){  
    		e.printStackTrace();  
    	}  
    	return result;  
    }  
    
    public static void main(String[] args) {
        try {
        	JXCApiUtil j = new JXCApiUtil();
        	Map<String, String> map = new HashMap<>();
        	map.put("account", "erp_5");
        	map.put("pwd", "123456");
    		String result = j.doPost("https://jxc.deeptel.com.cn" + "/erp/b/login", map , null);
    		System.err.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

@SuppressWarnings("deprecation")
class SSLClient extends DefaultHttpClient{  
	public SSLClient() throws Exception{  
        super();  
        SSLContext ctx = SSLContext.getInstance("TLS");  
        X509TrustManager tm = new X509TrustManager() {  
                @Override  
                public void checkClientTrusted(X509Certificate[] chain,  
                        String authType) throws CertificateException {  
                }  
                @Override  
                public void checkServerTrusted(X509Certificate[] chain,  
                        String authType) throws CertificateException {  
                }  
                @Override  
                public X509Certificate[] getAcceptedIssuers() {  
                    return null;  
                }  
        };  
        ctx.init(null, new TrustManager[]{tm}, null);  
        SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
        ClientConnectionManager ccm = this.getConnectionManager();  
        SchemeRegistry sr = ccm.getSchemeRegistry();  
        sr.register(new Scheme("https", 443, ssf));  
    }  
}  
