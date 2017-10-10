<<<<<<< HEAD
package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author zhangmz
 * @since 2017-07-09
 */
@Data
@Accessors( chain = true )
@TableName( "bus_user" )
public class BusUser extends Model< BusUser > {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String  name;
    private String  email;
    private String  password;
    private Long    phone;
    private Integer gender;
    @TableField( "register_ip" )
    private String  registerIp;
    @TableField( "recent_ip" )
    private String  recentIp;
    private String  ctime;
    private String  mtime;
    private Integer status;
    @TableField( "fans_currency" )
    private Double  fansCurrency;
    private Double  flow;
    @TableField( "city_id" )
    private Integer cityId;
    private Integer level;
    private String  startTime;
    private String  endTime;
    private Integer years;
    private String  statTime;
    private Integer industryid;
    private Integer pid;
    @TableField( "sms_count" )
    private Integer smsCount;
    private Integer istechnique;
    private Integer advert;
    @TableField( "busmoney_level" )
    private Integer busmoneyLevel;
    private Integer regionids;
    private Integer isagent;
    private Integer agentid;
    private String  realname;
    @TableField( "login_source" )
    private Integer loginSource;
    @TableField( "is_binding" )
    private Integer isBinding;
    @TableField( "unbundling_time" )
    private String  unbundlingTime;
    @TableField( "fixed_phone" )
    private String  fixedPhone;
    @TableField( "customer_id" )
    private String  customerId;
    @TableField( "merchant_name" )
    private String  merchantName;
    @TableField( "add_source" )
    private Integer addSource;
    @TableField( "wz_auth" )
    private Integer wzAuth;

    @Override
    protected Serializable pkVal() {
	return this.id;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getRecentIp() {
		return recentIp;
	}

	public void setRecentIp(String recentIp) {
		this.recentIp = recentIp;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getFansCurrency() {
		return fansCurrency;
	}

	public void setFansCurrency(Double fansCurrency) {
		this.fansCurrency = fansCurrency;
	}

	public Double getFlow() {
		return flow;
	}

	public void setFlow(Double flow) {
		this.flow = flow;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public String getStatTime() {
		return statTime;
	}

	public void setStatTime(String statTime) {
		this.statTime = statTime;
	}

	public Integer getIndustryid() {
		return industryid;
	}

	public void setIndustryid(Integer industryid) {
		this.industryid = industryid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(Integer smsCount) {
		this.smsCount = smsCount;
	}

	public Integer getIstechnique() {
		return istechnique;
	}

	public void setIstechnique(Integer istechnique) {
		this.istechnique = istechnique;
	}

	public Integer getAdvert() {
		return advert;
	}

	public void setAdvert(Integer advert) {
		this.advert = advert;
	}

	public Integer getBusmoneyLevel() {
		return busmoneyLevel;
	}

	public void setBusmoneyLevel(Integer busmoneyLevel) {
		this.busmoneyLevel = busmoneyLevel;
	}

	public Integer getRegionids() {
		return regionids;
	}

	public void setRegionids(Integer regionids) {
		this.regionids = regionids;
	}

	public Integer getIsagent() {
		return isagent;
	}

	public void setIsagent(Integer isagent) {
		this.isagent = isagent;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(Integer loginSource) {
		this.loginSource = loginSource;
	}

	public Integer getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(Integer isBinding) {
		this.isBinding = isBinding;
	}

	public String getUnbundlingTime() {
		return unbundlingTime;
	}

	public void setUnbundlingTime(String unbundlingTime) {
		this.unbundlingTime = unbundlingTime;
	}

	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getAddSource() {
		return addSource;
	}

	public void setAddSource(Integer addSource) {
		this.addSource = addSource;
	}

	public Integer getWzAuth() {
		return wzAuth;
	}

	public void setWzAuth(Integer wzAuth) {
		this.wzAuth = wzAuth;
	}

	@Override
    public String toString() {
	return "BusUser{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + ", gender=" + gender + ", registerIp=" + registerIp
			+ ", recentIp=" + recentIp + ", ctime=" + ctime + ", mtime=" + mtime + ", status=" + status + ", fansCurrency=" + fansCurrency + ", flow=" + flow
			+ ", cityId=" + cityId + ", level=" + level + ", startTime=" + startTime + ", endTime=" + endTime + ", years=" + years + ", statTime=" + statTime
			+ ", industryid=" + industryid + ", pid=" + pid + ", smsCount=" + smsCount + ", istechnique=" + istechnique + ", advert=" + advert + ", busmoneyLevel="
			+ busmoneyLevel + ", regionids=" + regionids + ", isagent=" + isagent + ", agentid=" + agentid + ", realname=" + realname + ", loginSource=" + loginSource
			+ ", isBinding=" + isBinding + ", unbundlingTime=" + unbundlingTime + ", fixedPhone=" + fixedPhone + ", customerId=" + customerId + ", merchantName="
			+ merchantName + ", addSource=" + addSource + ", wzAuth=" + wzAuth + "}";
    }
}
=======
package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author zhangmz
 * @since 2017-07-09
 */
@Data
@Accessors( chain = true )
@TableName( "bus_user" )
public class BusUser extends Model< BusUser > {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String  name;
    private String  email;
    private String  password;
    private Long    phone;
    private Integer gender;
    @TableField( "register_ip" )
    private String  registerIp;
    @TableField( "recent_ip" )
    private String  recentIp;
    private String  ctime;
    private String  mtime;
    private Integer status;
    @TableField( "fans_currency" )
    private Double  fansCurrency;
    private Double  flow;
    @TableField( "city_id" )
    private Integer cityId;
    private Integer level;
    private String  startTime;
    private String  endTime;
    private Integer years;
    private String  statTime;
    private Integer industryid;
    private Integer pid;
    @TableField( "sms_count" )
    private Integer smsCount;
    private Integer istechnique;
    private Integer advert;
    @TableField( "busmoney_level" )
    private Integer busmoneyLevel;
    private Integer regionids;
    private Integer isagent;
    private Integer agentid;
    private String  realname;
    @TableField( "login_source" )
    private Integer loginSource;
    @TableField( "is_binding" )
    private Integer isBinding;
    @TableField( "unbundling_time" )
    private String  unbundlingTime;
    @TableField( "fixed_phone" )
    private String  fixedPhone;
    @TableField( "customer_id" )
    private String  customerId;
    @TableField( "merchant_name" )
    private String  merchantName;
    @TableField( "add_source" )
    private Integer addSource;
    @TableField( "wz_auth" )
    private Integer wzAuth;

    @Override
    protected Serializable pkVal() {
	return this.id;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Long getPhone() {
	return phone;
    }

    public void setPhone(Long phone) {
	this.phone = phone;
    }

    public Integer getGender() {
	return gender;
    }

    public void setGender(Integer gender) {
	this.gender = gender;
    }

    public String getRegisterIp() {
	return registerIp;
    }

    public void setRegisterIp(String registerIp) {
	this.registerIp = registerIp;
    }

    public String getRecentIp() {
	return recentIp;
    }

    public void setRecentIp(String recentIp) {
	this.recentIp = recentIp;
    }

    public String getCtime() {
	return ctime;
    }

    public void setCtime(String ctime) {
	this.ctime = ctime;
    }

    public String getMtime() {
	return mtime;
    }

    public void setMtime(String mtime) {
	this.mtime = mtime;
    }

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public Double getFansCurrency() {
	return fansCurrency;
    }

    public void setFansCurrency(Double fansCurrency) {
	this.fansCurrency = fansCurrency;
    }

    public Double getFlow() {
	return flow;
    }

    public void setFlow(Double flow) {
	this.flow = flow;
    }

    public Integer getCityId() {
	return cityId;
    }

    public void setCityId(Integer cityId) {
	this.cityId = cityId;
    }

    public Integer getLevel() {
	return level;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public String getStartTime() {
	return startTime;
    }

    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    public String getEndTime() {
	return endTime;
    }

    public void setEndTime(String endTime) {
	this.endTime = endTime;
    }

    public Integer getYears() {
	return years;
    }

    public void setYears(Integer years) {
	this.years = years;
    }

    public String getStatTime() {
	return statTime;
    }

    public void setStatTime(String statTime) {
	this.statTime = statTime;
    }

    public Integer getIndustryid() {
	return industryid;
    }

    public void setIndustryid(Integer industryid) {
	this.industryid = industryid;
    }

    public Integer getPid() {
	return pid;
    }

    public void setPid(Integer pid) {
	this.pid = pid;
    }

    public Integer getSmsCount() {
	return smsCount;
    }

    public void setSmsCount(Integer smsCount) {
	this.smsCount = smsCount;
    }

    public Integer getIstechnique() {
	return istechnique;
    }

    public void setIstechnique(Integer istechnique) {
	this.istechnique = istechnique;
    }

    public Integer getAdvert() {
	return advert;
    }

    public void setAdvert(Integer advert) {
	this.advert = advert;
    }

    public Integer getBusmoneyLevel() {
	return busmoneyLevel;
    }

    public void setBusmoneyLevel(Integer busmoneyLevel) {
	this.busmoneyLevel = busmoneyLevel;
    }

    public Integer getRegionids() {
	return regionids;
    }

    public void setRegionids(Integer regionids) {
	this.regionids = regionids;
    }

    public Integer getIsagent() {
	return isagent;
    }

    public void setIsagent(Integer isagent) {
	this.isagent = isagent;
    }

    public Integer getAgentid() {
	return agentid;
    }

    public void setAgentid(Integer agentid) {
	this.agentid = agentid;
    }

    public String getRealname() {
	return realname;
    }

    public void setRealname(String realname) {
	this.realname = realname;
    }

    public Integer getLoginSource() {
	return loginSource;
    }

    public void setLoginSource(Integer loginSource) {
	this.loginSource = loginSource;
    }

    public Integer getIsBinding() {
	return isBinding;
    }

    public void setIsBinding(Integer isBinding) {
	this.isBinding = isBinding;
    }

    public String getUnbundlingTime() {
	return unbundlingTime;
    }

    public void setUnbundlingTime(String unbundlingTime) {
	this.unbundlingTime = unbundlingTime;
    }

    public String getFixedPhone() {
	return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
	this.fixedPhone = fixedPhone;
    }

    public String getCustomerId() {
	return customerId;
    }

    public void setCustomerId(String customerId) {
	this.customerId = customerId;
    }

    public String getMerchantName() {
	return merchantName;
    }

    public void setMerchantName(String merchantName) {
	this.merchantName = merchantName;
    }

    public Integer getAddSource() {
	return addSource;
    }

    public void setAddSource(Integer addSource) {
	this.addSource = addSource;
    }

    public Integer getWzAuth() {
	return wzAuth;
    }

    public void setWzAuth(Integer wzAuth) {
	this.wzAuth = wzAuth;
    }

    @Override
    public String toString() {
	return "BusUser{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + ", gender=" + gender + ", registerIp=" + registerIp
			+ ", recentIp=" + recentIp + ", ctime=" + ctime + ", mtime=" + mtime + ", status=" + status + ", fansCurrency=" + fansCurrency + ", flow=" + flow
			+ ", cityId=" + cityId + ", level=" + level + ", startTime=" + startTime + ", endTime=" + endTime + ", years=" + years + ", statTime=" + statTime
			+ ", industryid=" + industryid + ", pid=" + pid + ", smsCount=" + smsCount + ", istechnique=" + istechnique + ", advert=" + advert + ", busmoneyLevel="
			+ busmoneyLevel + ", regionids=" + regionids + ", isagent=" + isagent + ", agentid=" + agentid + ", realname=" + realname + ", loginSource=" + loginSource
			+ ", isBinding=" + isBinding + ", unbundlingTime=" + unbundlingTime + ", fixedPhone=" + fixedPhone + ", customerId=" + customerId + ", merchantName="
			+ merchantName + ", addSource=" + addSource + ", wzAuth=" + wzAuth + "}";
    }
}
>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
