package com.ljs.bo;

/**
 * Created by lala on 16/8/24.
 */
public class AppInfo {

    private String appKey;
    private String appName;

    public AppInfo(String appKey, String appName) {
        this.appKey = appKey;
        this.appName = appName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
