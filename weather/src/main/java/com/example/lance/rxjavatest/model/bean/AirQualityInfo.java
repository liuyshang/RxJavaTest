package com.example.lance.rxjavatest.model.bean;

/**
 * author: Lance
 * time: 2016/2/26 17:38
 * e-mail: lance.cao@anarry.com
 */
public class AirQualityInfo {

    /**
     * errNum : 0
     * retMsg : success
     * retData : {"city":"北京","time":"2014-12-04T13:00:00Z","aqi":38,"level":"优","core":""}
     */

    private int errNum;
    private String retMsg;
    /**
     * city : 北京
     * time : 2014-12-04T13:00:00Z
     * aqi : 38
     * level : 优
     * core :
     */

    private RetDataEntity retData;

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public static class RetDataEntity {
        private String city;
        private String time;
        private int aqi;
        private String level;
        private String core;

        public void setCity(String city) {
            this.city = city;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setAqi(int aqi) {
            this.aqi = aqi;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setCore(String core) {
            this.core = core;
        }

        public String getCity() {
            return city;
        }

        public String getTime() {
            return time;
        }

        public int getAqi() {
            return aqi;
        }

        public String getLevel() {
            return level;
        }

        public String getCore() {
            return core;
        }
    }
}
