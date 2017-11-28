package com.gt.hotel.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.exception.SignException;
import com.gt.api.util.HttpClienUtils;
import com.gt.api.util.sign.SignHttpUtils;
import com.gt.entityBo.ErpRefundBo;
import com.gt.hotel.properties.WebServerConfigurationProperties;

/**
 * 多粉接口
 *
 * @author Reverien9@gmail.com
 * 2017年11月7日 上午10:36:52
 */
@Component
public class WXMPApiUtil {

    @Autowired
    private WebServerConfigurationProperties webServerConfigurationProperties;

    /**
     * 获取字典信息
     *
     * @param style 字典属性（不能为null）
     * @return
     * @throws SignException
     */
    public String getDictKey(String style) throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("style", style);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("dictKey");
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, webServerConfigurationProperties.getWxmpService().getSignKey());
        return result;
    }

    /**
     * 对照李逢喜的接口文档
     *
     * @param param 参数
     * @param url   路径
     * @return
     * @throws SignException
     */
    private JSONObject getLApi(JSONObject param, String url)
            throws SignException {
        JSONObject result = HttpClienUtils.reqPostUTF8(
                JSONObject.toJSONString(param), url, JSONObject.class, webServerConfigurationProperties.getWxmpService().getSignKey());
        return result;
    }

    /**
     * 对照陈丹的接口文档
     *
     * @param param 参数
     * @param url   路径
     * @return
     * @throws SignException
     */
    @SuppressWarnings("rawtypes")
    private JSONObject getCApi(Map param, String url) throws SignException {
        String s = SignHttpUtils.WxmppostByHttp(url, param, webServerConfigurationProperties.getWxmpService().getSignKey());
        return JSONObject.parseObject(s);
    }

    /**
     * 对照彭江丽的接口文档
     *
     * @param param 参数
     * @param url   路径
     * @return
     * @throws SignException
     */
    private JSONObject getPApi(Object param, String url) throws SignException {
        String s = SignHttpUtils.WxmppostByHttp(url, param, webServerConfigurationProperties.getMemberService().getSignKey());
        JSONObject result = JSONObject.parseObject(s);
        return result;
    }

    /**
     * 根据商家ID判断是否开通某个erp 功能
     *
     * @param userId 用户ID
     * @return
     * @throws SignException
     */
    public String isErpCount(Integer userId) throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("userId", userId);
        paramObj.put("modelstyle", "9");
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("isErpCount");
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, webServerConfigurationProperties.getWxmpService().getSignKey());
        return result;
    }

    /**
     * Socket推送
     *
     * @param pushName 推送人
     * @param pushMsg  推送内容
     * @return
     * @throws SignException
     */
    /*public JSONObject socketPush(String pushName, String pushMsg)
            throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("pushName", pushName);
        // paramObj.put("pushStyle", pushStyle);
        paramObj.put("pushMsg", pushMsg);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("socketPush");
        return  SignHttpUtils.WxmppostByHttp(url, paramObj, webServerConfigurationProperties.getWxmpService().getSignKey());
    }*/

    /**
     * 商家/员工获取ERP列表菜单
     *
     * @param style      登录人属性0是员工，1是老板（必填）
     * @param userId     登录人的ID（必填）
     * @param loginuc    登陆属性 0是电脑端，1是UC端
     * @param levelModel 菜单版本，登陆第一次不需要传，切换菜单时，请传该参数
     * @return
     * @throws SignException
     */
    public String getERPMenus(Integer style, Integer userId, Integer loginuc,
                              Integer levelModel) throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("style", style);
        paramObj.put("userId", userId);
        paramObj.put("loginuc", loginuc);
        paramObj.put("erpstyle", 9);
        paramObj.put("levelModel", levelModel);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("erpMenus");
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, webServerConfigurationProperties.getWxmpService().getSignKey());
        return result;
    }

    /**
     * 根据门店ID获取员工列表分页功能（支持员工姓名，手机登模糊查询）
     *
     * @param shopId   门店ID
     * @param pageSize 每页数量（可以为空）
     * @param page     第几页(pageSize,page为空时，代表查询店铺下面的所有员工)
     * @param name     员工姓名（模糊查询，可以为空）
     * @param phone    员工电话（模糊查询，可以为空）
     * @return
     * @throws SignException
     */
    public String getStaffListShopId(Integer shopId, Integer pageSize,
                                     Integer page, String name, String phone) throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("shopId", shopId);
        paramObj.put("pageSize", pageSize);
        paramObj.put("page", page);
        paramObj.put("name", name);
        paramObj.put("phone", phone);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("staffListByShopId");
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, webServerConfigurationProperties.getWxmpService().getSignKey());
        return result;
    }

    public JSONObject getAllStaffShopId(Integer shopId, String name, String phone, Integer page, Integer pageSize) {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("shopId", shopId);
        paramObj.put("name", name);
        paramObj.put("phone", phone);
        paramObj.put("page", page);
        paramObj.put("pageSize", pageSize);
        JSONObject result = null;
        try {
            result = getCApi(paramObj, webServerConfigurationProperties.getWxmpService().getApiMap().get("staffListByShopId"));
        } catch (SignException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据门店ID获取管理员（子账号）分页列表信息
     *
     * @param shopId   门店ID
     * @param pageSize 每页数量（可以为空）
     * @param page     第几页 从1 开始(pageSize,page为空时，代表查询店铺下面的所有员工)
     * @return
     * @throws SignException
     */
    public String getChildListShopId(Integer shopId, Integer pageSize,
                                     Integer page) throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("shopId", shopId);
        paramObj.put("pageSize", pageSize);
        paramObj.put("page", page);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("childListByShopId");
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, webServerConfigurationProperties.getWxmpService().getSignKey());
        return result;
    }

    /**
     * 发送短信(旧接口)
     *
     * @param busId   商家ID
     * @param phone   手机号
     * @param content 内容
     * @return
     * @throws SignException
     */
    public JSONObject sendMsg(Integer busId, String phone, String content)
            throws SignException {
        Map<String, Object> oldApiSms = new HashMap<String, Object>();
        oldApiSms.put("mobiles", phone);
        oldApiSms.put("content", content);
        oldApiSms.put("company", "(多粉平台)");
        oldApiSms.put("busId", busId);
        oldApiSms.put("model", 7);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("sendSMSOld");
        String result = SignHttpUtils.postByHttp(url, oldApiSms, webServerConfigurationProperties.getWxmpService().getSignKey());
        return JSONObject.parseObject(result);
    }

    /**
     * 发送短信(模板接口)
     *
     * @param mobile    手机号码,可多个号码
     * @param paramsStr 内容
     * @param busId     商家ID
     * @param tmplId    短信模板id
     * @return
     * @throws SignException
     */
    public JSONObject sendSmsNew(String mobile, String paramsStr, Integer busId, Long tmplId) throws SignException {
        Map<String, Object> newApiSms = new HashMap<String, Object>();
        newApiSms.put("mobile", mobile);
        newApiSms.put("paramsStr", paramsStr);
        newApiSms.put("busId", busId);
        newApiSms.put("model", 7);
        newApiSms.put("tmplId", tmplId);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("sendSmsNew");
        String result = SignHttpUtils.postByHttp(url, newApiSms, webServerConfigurationProperties.getWxmpService().getSignKey());
        return JSONObject.parseObject(result);
    }

    /**
     * 根据ID获取门店信息
     *
     * @param shopid 门店ID
     * @return
     * @throws SignException
     */
    public JSONObject getShopById(Integer shopid) throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("reqdata", shopid);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("findShopInfoByShopId");
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, webServerConfigurationProperties.getWxmpService().getSignKey());
        System.err.println(result);
        JSONObject json = null;
        if (result != null && result.trim().length() > 0) {
            json = JSONObject.parseObject(result);
        }
        return json;
    }

    /**
     * 根据商家ID获取所有门店
     *
     * @param busId 商家ID
     * @return
     * @throws SignException
     */
    public JSONObject queryWxShopByBusId(Integer busId)
            throws SignException {
        JSONObject param = new JSONObject();
        param.put("reqdata", busId);
        return getLApi(param, webServerConfigurationProperties.getWxmpService().getApiMap().get("findWxShopListByBusId"));
    }

    /**
     * 查询会员
     *
     * @param shopid 门店id
     * @param busId  商家id
     * @param number 卡号或手机号
     * @return JSONObject
     * @throws SignException
     */
    public JSONObject queryMember(Integer shopid, Integer busId, String number)
            throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("shopId", shopid);
        paramObj.put("busId", busId);
        paramObj.put("cardNo", number);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("findMemberCard");
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, webServerConfigurationProperties.getMemberService().getSignKey());
        JSONObject json = null;
        if (result != null && result.trim().length() > 0) {
            json = JSONObject.parseObject(result);
        }
        return json;
    }

    /**
     * 根据memberId和门店查询会员数据 返回数据包含会员信息、微信卡券、多粉卡券
     *
     * @param shopid   门店id
     * @param memberid 粉丝id
     * @return
     * @throws SignException
     */
    public JSONObject findCardByMembeId(Integer shopid, Integer memberid)
            throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("shopId", shopid);
        paramObj.put("memberId", memberid);
        String url = webServerConfigurationProperties.getWxmpService().getApiMap().get("findCardByMembeId");
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj,
                webServerConfigurationProperties.getMemberService().getSignKey());
        System.err.println(result);
        JSONObject json = null;
        if (result != null && result.trim().length() > 0) {
            json = JSONObject.parseObject(result);
        }
        return json;
    }

    /**
     * 微信退款
     *
     * @param appid      公众号或小程序appid
     * @param mchid      支付商户号
     * @param sysOrderNo 订单号
     * @param refundFee  退款金额
     * @param totalFee   总金额
     * @return
     * @throws SignException
     */
    public JSONObject wxmemberPayRefund(String appid, String mchid, String sysOrderNo, Double refundFee, Double totalFee)
            throws SignException {
        JSONObject param = new JSONObject();
        param.put("appid", appid);
        param.put("mchid", mchid);
        param.put("sysOrderNo", sysOrderNo);
        param.put("refundFee", refundFee);
        param.put("totalFee", totalFee);
        JSONObject result = getLApi(param, webServerConfigurationProperties.getWxmpService().getApiMap().get("wxmemberPayRefund"));
        return result;
    }

    /**
     * 会员结算退款
     *
     * @param bo
     * @return
     * @throws SignException
     */
    public JSONObject memberRefundErp(ErpRefundBo bo)
            throws SignException {
        JSONObject result = getPApi(bo, webServerConfigurationProperties.getWxmpService().getApiMap().get("refundErp"));
        return result;
    }

    /**
     * 根据粉丝id获取粉丝信息
     *
     * @param cardNo 会员卡
     * @param busId  商家ID
     * @param shopId 门店ID
     * @return JSONObject 会员卡信息
     * @throws SignException
     */
    public JSONObject findMemberCard(String cardNo, Integer busId, Integer shopId) throws SignException {
        Map<String, Object> param = new HashMap<>();
        param.put("cardNo", cardNo);
        param.put("busId", busId);
        param.put("shopId", shopId);
        JSONObject result = getPApi(param, webServerConfigurationProperties.getMemberService().getApiMap().get("findMemberCard"));
        return result;
    }

    /**
     * socket推送人
     *
     * @param pushName  推送人（不能为null）
     * @param pushStyle 推送属性（有属性的要填，没有属性的不用填）
     * @param pushMsg   推送内容
     * @return
     * @throws SignException
     */
    public JSONObject getSocketApi(String pushName, String pushStyle, String pushMsg)
            throws SignException {
        JSONObject param = new JSONObject();
        param.put("pushName", pushName);
        if (pushStyle != null) {
            param.put("pushStyle", pushStyle);
        }
        param.put("pushMsg", pushMsg);
        JSONObject result = getCApi(param, webServerConfigurationProperties.getWxmpService().getApiMap().get("socketPush"));
        return result;
    }

    /**
     * 获取WxPulbic对象
     *
     * @param busId 商家ID
     * @return
     * @throws SignException
     */
    public JSONObject getWxPulbicMsg(Integer busId) throws SignException {
        Map<String, Object> param = new HashMap<>();
        param.put("busId", busId);
        JSONObject result = getCApi(param, webServerConfigurationProperties.getWxmpService().getApiMap().get("getWxPulbicMsg"));
        return result;
    }


    /**
     * 设置redsi 存储
     *
     * @param otherRedisKey rediskey
     * @param redirectUrl   重定向地址
     * @param setime        时效
     * @return JSONObject
     * @throws SignException
     */
    public JSONObject setRedisStorage(String otherRedisKey, String redirectUrl, Long setime) throws SignException {
        if (setime == null || setime == 0) {
            setime = 300L;
        }
        JSONObject redisMap = new JSONObject();
        redisMap.put("redisKey", otherRedisKey);
        redisMap.put("redisValue", redirectUrl);
        redisMap.put("setime", setime);
        return getLApi(redisMap, webServerConfigurationProperties.getWxmpService().getApiMap().get("setRedisStorage"));
    }

    /**
     * 根据商家ID查询 商户号对象
     * @param busId
     * @return com.gt.api.bean.session.WxPublicUsers
     * @throws SignException
     */
    public JSONObject selectByUserId(Integer busId) throws SignException {
    	JSONObject re = new JSONObject();
    	re.put("reqdata", busId);
    	return getLApi(re, webServerConfigurationProperties.getWxmpService().getApiMap().get("selectByUserId"));
    }

    /**
     * 商家/员工  获取管理的ERP列表
     * @param loginStyle 登录人属性0是员工，1是老板（必填）
     * @param userId 登录人的ID（必填）
     * @return /hotel-entity/src/main/java/com/gt/hotel/other/MenusLevelList.java
     * @throws SignException
     */
    public JSONObject getErpListApi(Integer loginStyle, Integer userId) throws SignException {
    	Map<String, Object> param = new HashMap<>();
    	param.put("loginStyle", loginStyle);
    	param.put("userId", userId);
		return getCApi(param , webServerConfigurationProperties.getWxmpService().getApiMap().get("getErpListApi"));
	}

    /**
     * 商家/员工获取ERP列表菜单
     * @param style 登录人属性0是员工，1是老板（必填）
     * @param userId 登录人的ID（必填）
     * @param loginuc 登陆属性 0是电脑端，1是UC端
     * @param levelModel 菜单版本，登陆第一次不需要传，切换菜单时，请传该参数
     * @return /hotel-entity/src/main/java/com/gt/hotel/other/ErpMenus.java
     * @throws SignException
     */
    public JSONObject getMenus(Integer style, Integer userId, Integer loginuc, String levelModel) throws SignException {
    	Map<String, Object> param = new HashMap<>();
    	param.put("style", style);
    	param.put("userId", userId);
    	param.put("loginuc", loginuc);
    	param.put("erpstyle", 9);
    	if(levelModel != null) {
    		param.put("levelModel", levelModel);
    	}
    	return getCApi(param , webServerConfigurationProperties.getWxmpService().getApiMap().get("getMenus"));
    }

    /**
     * erp商家是否有商城功能
     * @param style 登录人属性0是员工，1是老板（必填）
     * @param userId 登录人的ID（必填）
     * @return error	Integer	0 代表商家有商城菜单，其余代表没有商城  message	String	提示消息（为什么没有商城的原因）
     * @throws SignException
     */
    public JSONObject getMall(Integer style, Integer userId) throws SignException {
    	Map<String, Object> param = new HashMap<>();
    	param.put("style", style);
    	param.put("userId", userId);
    	return getCApi(param , webServerConfigurationProperties.getWxmpService().getApiMap().get("getMall"));
    }

    /**
     * erp三级菜单接口
     * @param pidUrl 父类菜单的url
     * @param sonUrls 子类菜单的url,多个用,区分
     * @param loginStyle 登录人属性0是员工，1是老板（必填）
     * @param userId 登录人的ID（必填）
     * @return /hotel-entity/src/main/java/com/gt/hotel/other/Menuslist.java
     * @throws SignException
     */
    public JSONObject getMenusThree(String pidUrl, String sonUrls, String loginStyle, String userId) throws SignException {
    	Map<String, Object> param = new HashMap<>();
    	param.put("pidUrl", pidUrl);
    	param.put("sonUrls", sonUrls);
    	param.put("loginStyle", loginStyle);
    	param.put("userId", userId);
    	param.put("erpstyle", 9);
    	return getCApi(param , webServerConfigurationProperties.getWxmpService().getApiMap().get("getMenusThree"));
    }



    public static void main(String[] args) {
        try {
            Map<String, Object> param = new HashMap<String, Object>();
//        	param.put("style", 1193);
//        	String url = "https://deeptel.com.cn" + "/8A5DA52E/dictApi/getDictApi.do";
        	param.put("loginStyle", "1");
        	param.put("userId", 33);
        	param.put("loginuc", 0);
        	param.put("pidUrl", "/erporder/iframe.do");
        	param.put("sonUrls", "/erporder/takeway/index.do");
        	param.put("erpstyle", 1);
//        	param.put("levelModel", 1);
//            param.put("reqdata", 33);
            String url = "https://deeptel.com.cn" + "/8A5DA52E/ErploginApi/getMenusThree.do";
            String result = SignHttpUtils.WxmppostByHttp(url, param, "WXMP2017");
//            JSONObject result = HttpClienUtils.reqPostUTF8(JSONObject.toJSONString(param), url, JSONObject.class, "WXMP2017");

//        	param.put("cardNo", "13433550667");
//        	param.put("memberId", 1071);
//        	param.put("shopId", 29);
//        	String url = "http://member.yifriend.net" + "/memberAPI/member/findCardByMembeId";
//        	String result = SignHttpUtils.WxmppostByHttp(url, param, "MV8MMFQUMU1HJ6F2GNH40ZFJJ7Q8LNVM");
//
            System.err.println(result);

//            String url = "http://member.yifriend.net/" + "memberAPI/member/findMemberByIds";
//            String signKey = "MV8MMFQUMU1HJ6F2GNH40ZFJJ7Q8LNVM";
//            Map<String, Object> params = new HashMap<>();
//            params.put("busId", 33);
//            params.put("ids", "1071");
//            String result = SignHttpUtils.WxmppostByHttp(url, params, signKey);
//            System.err.println(result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

