package me.eagzzycsl.netquality;

public enum MyInfoType {
    stamp("stamp", null),
    netType("net_type", "网络类型"),
    timeZone("time_zone", "时区"),
    date("date", "日期"),
    time("time", "时间"),
    IMSI("imsi", "IMSI"),
    IMEI("imei", "IMEI"),
    phoneName("phone_name", "终端名称"),
    osVersion("os_version", "操作系统版本"),
    operator("operator", "运营商"),
    longitude("longitude", "经度"),
    latitude("latitude", "纬度"),
    ip("ip", "ip地址");

    private final String name;
    private final String key;

    MyInfoType(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public final String getName() {
        return this.name;

    }

    public final String getKey() {
        return this.key;
    }

    public static MyInfoType getEnum(String key) {
        for (MyInfoType myInfoType : MyInfoType.values()) {
            if (myInfoType != stamp && myInfoType.getKey().equals(key)) {
                return myInfoType;
            }
        }
        return null;
    }

}