package me.eagzzycsl.netquality;

public enum MyInfoType {
    stamp("stamp", null),
    date("date", "日期"),
    time("time", "时间");
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