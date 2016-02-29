package com.example.lance.rxjavatest.model.bean;

/**
 * author: Lance
 * time: 2016/2/23 18:49
 * e-mail: lance.cao@anarry.com
 */
public class IPAddressInfo {
    /**
     * errNum : 0
     * errMsg : success
     * retData : {"ip":"117.89.35.58","country":"中国","province":"江苏","City":"南京","district":"鼓楼","carrier":"中国电信"}
     */

    private int errNum;
    private String errMsg;
    /**
     * ip : 117.89.35.58
     * country : 中国
     * province : 江苏
     * City : 南京
     * district : 鼓楼
     * carrier : 中国电信
     */

    private RetDataEntity retData;

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public static class RetDataEntity {
        private String ip;
        private String country;
        private String province;
        private String city;
        private String district;
        private String carrier;

        public void setIp(String ip) {
            this.ip = ip;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public String getIp() {
            return ip;
        }

        public String getCountry() {
            return country;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getDistrict() {
            return district;
        }

        public String getCarrier() {
            return carrier;
        }
    }
}
