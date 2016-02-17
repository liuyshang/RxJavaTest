package com.example.lance.rxjavatest.model.bean;

/**
 * author: Lance
 * time: 2016/1/29 14:50
 * e-mail: lance.cao@anarry.com
 */
public class MobileInfo {

    /**
     * 地区码
     * */
    private String areaCode;

    /**
     * 城市
     * */
    private String city;

    /**
     * 运行商
     * */
    private String operator;

    /**
     * 手机号
     * */
    private String phone;

    /**
     * 邮编
     * */
    private String postCode;

    /**
     * 省份
     * */
    private String province;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
