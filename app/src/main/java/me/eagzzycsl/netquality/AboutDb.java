package me.eagzzycsl.netquality;


public interface AboutDb {
    String DB_NAME = "NetQuality";
    int DB_VERSION = 1;

    interface TABLE_History {
        String TABLE_NAME = "history";

        interface FILED {
            String ID = "_ID";
        }
    }
}
