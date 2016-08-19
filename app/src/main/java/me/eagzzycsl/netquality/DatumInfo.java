package me.eagzzycsl.netquality;

public final class DatumInfo extends BaseDatum {
    private MyInfoType myInfoType;
    private String value;

    public DatumInfo(MyInfoType myInfoType, String value) {
        this.myInfoType = myInfoType;
        this.value = value;
    }

    public final String getValue() {
        return this.value;
    }

    public final String getKey() {
        return this.myInfoType.getKey();
    }

    public final String getName() {
        return this.myInfoType.getName();
    }

}
