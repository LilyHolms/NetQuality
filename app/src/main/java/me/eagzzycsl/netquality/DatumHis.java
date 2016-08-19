package me.eagzzycsl.netquality;

public class DatumHis extends BaseDatum {
    private String stamp;//某条记录的时间戳

    public DatumHis(String stamp) {
        this.stamp = stamp;
    }

    public String getStamp() {
        return this.stamp;
    }
}
