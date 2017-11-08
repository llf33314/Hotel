package com.gt.hotel.util;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.exception.SignException;
import com.gt.api.util.HttpClienUtils;
import com.gt.api.util.sign.SignHttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 多粉接口
 * @author Reverien9@gmail.com
 * 2017年11月7日 上午10:36:52
 */
@Component
public class WXMPApiUtil {

    @Value("${wxmp.api.sign}")
    private String SIGN_KEY;

    /**
     * 会员签名 参考 https://member.deeptel.com.cn/swagger-ui.html#/
     */
    @Value("${wxmp.api.membersign}")
    private String MEMBER_SIGN_KEY;

    @Value("${wxmp.api.memberserverurl}")
    private String MEMBER_SERVER_URL;

    @Value("${wxmp.api.serverurl}")
    private String SERVER_URL;

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
        String url = SERVER_URL + "/8A5DA52E/dictApi/getDictApi.do";
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, SIGN_KEY);
        return result;
    }

    /**
     * 对照李逢喜的接口文档
     *
     * @param param 参数
     * @param _url  路径
     * @return
     * @throws SignException
     */
    private JSONObject getLApi(JSONObject param, String _url)
            throws SignException {
        String url = SERVER_URL + _url;
        JSONObject result = HttpClienUtils.reqPostUTF8(
                JSONObject.toJSONString(param), url, JSONObject.class, SIGN_KEY);
        return result;
    }

    /**
     * 对照陈丹的接口文档
     *
     * @param param 参数
     * @param _url  路径
     * @return
     * @throws SignException
     */
    @SuppressWarnings("rawtypes")
    private JSONObject getCApi(Map param, String _url) throws SignException {
    	System.err.println(SERVER_URL + _url);
        String s = SignHttpUtils.WxmppostByHttp(SERVER_URL + _url, param, SIGN_KEY);
//        String s = SignHttpUtils.WxmppostByHttp("https://deeptel.com.cn" + _url, param, "WXMP2017");
        JSONObject result = JSONObject.parseObject(s);
        return result;
    }

    /**
     * 对照彭江丽的接口文档
     *
     * @param param 参数
     * @param _url  路径
     * @return
     * @throws SignException
     */
    @SuppressWarnings("rawtypes")
    private JSONObject getPApi(Map param, String _url) throws SignException {
    	System.err.println(SERVER_URL + _url);
    	String s = SignHttpUtils.WxmppostByHttp(MEMBER_SERVER_URL + _url, param, MEMBER_SIGN_KEY);
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
        String url = SERVER_URL + "/8A5DA52E/busUserApi/getIsErpCount.do";
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, SIGN_KEY);
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
    public JSONObject socketPush(String pushName, String pushMsg)
            throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("pushName", pushName);
        // paramObj.put("pushStyle", pushStyle);
        paramObj.put("pushMsg", pushMsg);
        JSONObject result = getCApi(paramObj, "/8A5DA52E/socket/getSocketApi.do");
		return result;
    }

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
        String url = SERVER_URL + "/8A5DA52E/ErploginApi/getMenus.do";
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, SIGN_KEY);
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
        String url = SERVER_URL + "/8A5DA52E/staffApiMsg/getStaffListShopId.do";
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, SIGN_KEY);
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
            result = getCApi(paramObj, "/8A5DA52E/staffApiMsg/getStaffListShopId.do");
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
        String url = SERVER_URL + "/8A5DA52E/staffApiMsg/getChildListShopId.do";
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, SIGN_KEY);
        return result;
    }

    /**
     * 发送短信
     *
     * @param busid   商家ID
     * @param phone   手机号
     * @param content 内容
     * @return
     * @throws SignException
     */
    public boolean sendMsg(Integer busid, String phone, String content)
            throws SignException {
        Map<String, Object> OldApiSms = new HashMap<String, Object>();
        OldApiSms.put("mobiles", phone);
        OldApiSms.put("content", content);
        OldApiSms.put("company", "(多粉平台)");
        OldApiSms.put("busId", busid);
        OldApiSms.put("model", 7);
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("reqdata", OldApiSms);
        String url = SERVER_URL
                + "/8A5DA52E/smsapi/6F6D9AD2/79B4DE7C/sendSmsOld.do";
        // String result = SignHttpUtils.WxmppostByHttp(url, paramObj, SIGN_KEY);
        String result = SignHttpUtils.postByHttp(url, paramObj, SIGN_KEY);
        System.err.println(result);
        if (result != null) {
            JSONObject json = JSONObject.parseObject(result);
            if (json.getInteger("code") != null && json.getInteger("code") == 0)
                return true;
            else
                return false;
        } else
            return false;
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
        String url = SERVER_URL
                + "/8A5DA52E/shopapi/6F6D9AD2/79B4DE7C/getShopById.do";
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj, SIGN_KEY);
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
     * @param busid 商家ID
     * @return
     * @throws SignException
     */
//	public List<HotelWsWxShopInfoExtend> queryWxShopByBusId(Integer busid)
//			throws SignException {
//		JSONObject param = new JSONObject();
//		List<HotelWsWxShopInfoExtend> shops = null;
//		param.put("reqdata", busid);
//		JSONObject json = getLApi(param,
//				"/8A5DA52E/shopapi/6F6D9AD2/79B4DE7C/queryWxShopByBusId.do");
//		if (json.getBoolean("success")) {
//			shops = JSONArray.parseArray(json.getJSONArray("data").toJSONString(),
//					HotelWsWxShopInfoExtend.class);
//		}
//		return shops;
//	}
    public JSONObject queryWxShopByBusId(Integer busid)
            throws SignException {
        JSONObject param = new JSONObject();
        param.put("reqdata", busid);
        JSONObject json = getLApi(param,
                "/8A5DA52E/shopapi/6F6D9AD2/79B4DE7C/queryWxShopByBusId.do");
        return json;
    }

    /**
     * 查询会员
     *
     * @param shopid 门店id
     * @param busid  商家id
     * @param number 卡号或手机号
     * @return
     * @throws SignException
     */
    public JSONObject queryMember(Integer shopid, Integer busid, String number)
            throws SignException {
        Map<String, Object> paramObj = new HashMap<String, Object>();
        paramObj.put("shopId", shopid);
        paramObj.put("busId", busid);
        paramObj.put("cardNo", number);
        String url = SERVER_URL + "/memberAPI/member/findMemberCard";
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj,
                MEMBER_SIGN_KEY);
        System.err.println(result);
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
        String url = SERVER_URL + "/memberAPI/member/findCardByMembeId";
        String result = SignHttpUtils.WxmppostByHttp(url, paramObj,
                MEMBER_SIGN_KEY);
        System.err.println(result);
        JSONObject json = null;
        if (result != null && result.trim().length() > 0) {
            json = JSONObject.parseObject(result);
        }
        return json;
    }
    
    /**
     * 微信退款
     * @param appid 公众号或小程序appid
     * @param mchid 支付商户号
     * @param sysOrderNo 订单号
     * @param refundFee 退款金额
     * @param totalFee 总金额
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
        JSONObject result = getLApi(param, "/8A5DA52E/payApi/6F6D9AD2/79B4DE7C/wxmemberPayRefund.do");
        return result;
    }
    
    /**
     * socket推送人
     * @param pushName 推送人（不能为null）
     * @param pushStyle 推送属性（有属性的要填，没有属性的不用填）
     * @param pushMsg 推送内容
     * @return
     * @throws SignException
     */
    public JSONObject getSocketApi(String pushName, String pushStyle, String pushMsg)
    		throws SignException {
    	JSONObject param = new JSONObject();
    	param.put("appid", pushName);
    	if(pushStyle != null) {
    		param.put("mchid", pushStyle);
    	}
		param.put("sysOrderNo", pushMsg);
    	JSONObject result = getCApi(param, "/8A5DA52E/socket/getSocketApi.do");
    	return result;
    }
    
    /**
     * 获取WxPulbic对象
     * @param busId 商家ID
     * @return
     * @throws SignException
     */
    public JSONObject getWxPulbicMsg(Integer busId) throws SignException {
    	JSONObject param = new JSONObject();
    	param.put("busId", busId);
    	JSONObject result = getCApi(param, "/8A5DA52E/busUserApi/getWxPulbicMsg.do");
    	return result;
    }

    public static void main(String[] args) {
        try {
        	 Map<String, Object> paramObj = new HashMap<String, Object>();
             paramObj.put("style", 1198);
             String url = "https://deeptel.com.cn" + "/8A5DA52E/dictApi/getDictApi.do";
             String result = SignHttpUtils.WxmppostByHttp(url, paramObj, "WXMP2017");
            System.err.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

