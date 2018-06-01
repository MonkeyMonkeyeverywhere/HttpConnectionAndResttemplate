package com.qf.datatool.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CfgProperties {
    @Value("${qiaofang.updatePublishStatusUrl}")
    private String updatePublishStatusUrl;
    @Value("${qiaofang.shExecuteOnlineUrl}")
    private String shExecuteOnlineUrl;
    @Value("${qiaofang.gzExecuteOnlineUrl}")
    private String gzExecuteOnlineUrl;
    @Value("${qiaofang.appid}")
    private String appid;
    @Value("${qiaofang.appkey}")
    private String appkey;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getUpdatePublishStatusUrl() {
        return updatePublishStatusUrl;
    }

    public void setUpdatePublishStatusUrl(String updatePublishStatusUrl) {
        this.updatePublishStatusUrl = updatePublishStatusUrl;
    }

    public String getShExecuteOnlineUrl() {
        return shExecuteOnlineUrl;
    }

    public void setShExecuteOnlineUrl(String shExecuteOnlineUrl) {
        this.shExecuteOnlineUrl = shExecuteOnlineUrl;
    }

    public String getGzExecuteOnlineUrl() {
        return gzExecuteOnlineUrl;
    }

    public void setGzExecuteOnlineUrl(String gzExecuteOnlineUrl) {
        this.gzExecuteOnlineUrl = gzExecuteOnlineUrl;
    }
}
