package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商家里面的EPR员工管理
 * </p>
 *
 * @author 
 * @since 2017-09-04
 */
@ApiModel(description = "EPR员工管理")
@Data
@Accessors(chain = true)
@TableName("t_common_staff")
public class TCommonStaff extends Model<TCommonStaff> {
	
	/**
	 * 状态(0 正常用户)
	 */
	public static final Integer NORMAL_ACC = 0;
	/**
	 * 状态(1 冻结用户)
	 */
	public static final Integer FREEZE_ACC = 1;
	/**
	 * 性别(0表示男性)
	 */
	public static final Integer MALE = 0;
	/**
	 * 性别(1表示女性)
	 */
	public static final Integer FEMALE = 1;
	/**
	 * 是否删除：0正常
	 */
	public static final Integer NORMAL = 0;
	/**
	 * 是否删除：1删除
	 */
	public static final Integer DEL = 1;

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 登录名
     */
	@ApiModelProperty(value = "登录名")
	@TableField("login_name")
	private String loginName;
    /**
     * 密码
     */
	@ApiModelProperty(value = "密码")
	private String password;
    /**
     * 手机电话
     */
	@ApiModelProperty(value = "手机电话")
	private String phone;
    /**
     * 邮箱
     */
	@ApiModelProperty(value = "邮箱")
	private String email;
    /**
     * 工号
     */
	@ApiModelProperty(value = "工号")
	@TableField("job_number")
	private String jobNumber;
    /**
     * 性别(0表示男性, 1表示女性)
     */
	@ApiModelProperty(value = "性别(0表示男性, 1表示女性)")
	private Integer gender;
    /**
     * wx_shop 门店店铺id
     */
	@ApiModelProperty(value = "wx_shop 门店店铺id")
	@TableField("shop_id")
	private Integer shopId;
    /**
     * 状态(0 正常用户, 1 冻结用户)
     */
	@ApiModelProperty(value = "状态(0 正常用户, 1 冻结用户)")
	private Integer status;
    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
	@TableField("create_time")
	private String createTime;
    /**
     * 创建人，bus_user
     */
	@ApiModelProperty(value = "创建人，bus_user")
	@TableField("create_person")
	private Integer createPerson;
    /**
     * 最近登陆IP
     */
	@ApiModelProperty(value = "最近登陆IP")
	@TableField("recent_ip")
	private String recentIp;
    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
	private String remark;
    /**
     * 员工姓名
     */
	@ApiModelProperty(value = "员工姓名")
	private String name;
    /**
     * 是否删除：0正常，1删除
     */
	@ApiModelProperty(value = "是否删除：0正常，1删除")
	private Integer isdelect;
    /**
     * 职位id  取自t_common_erp_position
     */
	@ApiModelProperty(value = "职位id  取自t_common_erp_position")
	private Integer positionid;
    /**
     * 部门ID
     */
	@ApiModelProperty(value = "部门ID")
	private Integer branid;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TCommonStaff{" +
			"id=" + id +
			", loginName=" + loginName +
			", password=" + password +
			", phone=" + phone +
			", email=" + email +
			", jobNumber=" + jobNumber +
			", gender=" + gender +
			", shopId=" + shopId +
			", status=" + status +
			", createTime=" + createTime +
			", createPerson=" + createPerson +
			", recentIp=" + recentIp +
			", remark=" + remark +
			", name=" + name +
			", isdelect=" + isdelect +
			", positionid=" + positionid +
			", branid=" + branid +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}

	public String getRecentIp() {
		return recentIp;
	}

	public void setRecentIp(String recentIp) {
		this.recentIp = recentIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsdelect() {
		return isdelect;
	}

	public void setIsdelect(Integer isdelect) {
		this.isdelect = isdelect;
	}

	public Integer getPositionid() {
		return positionid;
	}

	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}

	public Integer getBranid() {
		return branid;
	}

	public void setBranid(Integer branid) {
		this.branid = branid;
	}
}
