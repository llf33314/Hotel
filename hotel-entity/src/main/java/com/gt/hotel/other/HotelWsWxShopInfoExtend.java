package com.gt.hotel.other;

import io.swagger.annotations.Api;

/**
 * 门店对象
 *
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月9日 上午11:46:26
 */
@Api(description = "门店对象")
public class HotelWsWxShopInfoExtend {
    private Integer id;
    /**
     * 多粉内部ID
     */
    private String  sid;
    /**
     * 微信的门店ID
     */
    private String  poiid;
    /**
     * 商家id
     */
    private Integer busId;
    /**
     * 门店名
     */
    private String  businessName;
    /**
     * 分店名
     */
    private String  branchName;
    /**
     * 门店类型主类型
     */
    private String  categories;
    /**
     * 二级类目
     */
    private String  twoCategories;
    /**
     * 三级类目
     */
    private String  threeCategories;
    /**
     * 电话(固定电话需加区号；区号、分机号均用“-”连接)
     */
    private String  telephone;
    /**
     * 人均价格(大于零的整数，须如实填写，默认单位为人民币)
     */
    private Integer avgPrice;
    /**
     * 开始营业时间
     */
    private String  startTime;
    /**
     * 关门时间
     */
    private String  endTime;
    /**
     * 特色服务
     */
    private String  special;
    /**
     * 简介
     */
    private String  introduction;
    /**
     * 推荐品
     */
    private String  recommend;
    /**
     * 门店所在的省份
     */
    private String  province;
    /**
     * 城市
     */
    private String  city;
    /**
     * 地区
     */
    private String  district;
    /**
     * 详细街道地址
     */
    private String  address;
    /**
     * 坐标类型(1 为火星坐标（目前只能选1）)
     */
    private Integer offsetType;
    /**
     * 经度
     */
    private String  longitude;
    /**
     * 纬度
     */
    private String  latitude;
    /**
     * 0：表示还没将门店信息发送到公众号审核，可修改全部内容；1：表示已发送给微信公众号审核  2 已审核通过 3审核通过后重新审核服务信息 4 送审被失败
     */
    private Integer status;
    /**
     * 单位信息
     */
    private String  detail;
    /**
     * 主店1：表示主店
     */
    private Integer mainShop;
    /**
     * 第一张图片
     */
    private String  imageUrl;

    private String createTime;

    private Integer availableState;

    private Integer updateStatus;

    private String adder;

    public void setMainShop(Integer mainShop) {
        this.mainShop = mainShop;
    }

    public Integer getMainShop() {
        return mainShop;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setAvailableState(Integer availableState) {
        this.availableState = availableState;
    }

    public Integer getAvailableState() {
        return availableState;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPoiid(String poiid) {
        this.poiid = poiid;
    }

    public String getPoiid() {
        return poiid;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setTwoCategories(String twoCategories) {
        this.twoCategories = twoCategories;
    }

    public String getTwoCategories() {
        return twoCategories;
    }

    public void setOffsetType(Integer offsetType) {
        this.offsetType = offsetType;
    }

    public Integer getOffsetType() {
        return offsetType;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setAdder(String adder) {
        this.adder = adder;
    }

    public String getAdder() {
        return adder;
    }

    public void setThreeCategories(String threeCategories) {
        this.threeCategories = threeCategories;
    }

    public String getThreeCategories() {
        return threeCategories;
    }

    public void setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getSpecial() {
        return special;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getCategories() {
        return categories;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    @Override
    public String toString() {
        return "HotelWsWxShopInfoExtend [id=" + id + ", sid=" + sid + ", poiid=" + poiid + ", busId=" + busId + ", businessName=" + businessName + ", branchName=" + branchName
                + ", categories=" + categories + ", twoCategories=" + twoCategories + ", threeCategories=" + threeCategories + ", telephone=" + telephone + ", avgPrice=" + avgPrice
                + ", startTime=" + startTime + ", endTime=" + endTime + ", special=" + special + ", introduction=" + introduction + ", recommend=" + recommend + ", province="
                + province + ", city=" + city + ", district=" + district + ", address=" + address + ", offsetType=" + offsetType + ", longitude=" + longitude + ", latitude=" + latitude
                + ", status=" + status + ", detail=" + detail + ", mainShop=" + mainShop + ", imageUrl=" + imageUrl + ", createTime=" + createTime + ", availableState="
                + availableState + ", updateStatus=" + updateStatus + ", adder=" + adder + "]";
    }

}
