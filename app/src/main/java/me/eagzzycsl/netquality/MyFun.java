package me.eagzzycsl.netquality;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Calendar;
import java.util.Enumeration;


public class MyFun {
    private Context context;
    private final String NO = "无法获取或不存在";

    public MyFun(Context context) {
        this.context = context;
    }

    public String getStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public String getNetType() {
        if (isWifi()) {
            return "WIFI";
        }
        int networkType = getTelephonyManger().getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN: {
                return "2G";
            }
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP: {
                return "3G";
            }
            case TelephonyManager.NETWORK_TYPE_LTE: {
                return "4G";
            }
            default:
                return NO;
        }
    }

    public String[] getTimeWithZone() {
        Calendar calendar = Calendar.getInstance();
        String timeZone = calendar.getTimeZone().getDisplayName();
        String date = android.text.format.DateFormat.format("yyyy-MM-dd", calendar).toString();
        String time = android.text.format.DateFormat.format("HH:mm:ss", calendar).toString();
        return new String[]{
                timeZone, date, time
        };

    }


    public String getIMSI() {
        String imsi = getTelephonyManger().getSubscriberId();
        return imsi == null ? NO : imsi;
    }

    public String getIMEI() {
        return getTelephonyManger().getDeviceId();
    }

    public String getPhoneName() {
        return android.os.Build.MODEL;
    }

    public String getOsVersion() {
        return String.valueOf(android.os.Build.VERSION.RELEASE);
    }

    public String getOperator() {
        return getTelephonyManger().getNetworkOperatorName();
    }

    public String getLongitude() {
        return null;
    }

    public String getLatitude() {
        return null;
    }

    public String getIp() {
        if (isWifi()) {
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            int ipAddress = wm.getConnectionInfo().getIpAddress();
            if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
                ipAddress = Integer.reverseBytes(ipAddress);
            }
            byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

            String ipAddressString;
            try {
                ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
            } catch (UnknownHostException ex) {
                ipAddressString = NO;
            }
            return ipAddressString;
        } else {
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface
                        .getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf
                            .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            } catch (SocketException ex) {
                ex.printStackTrace();
            }
            return NO;
        }
    }

    private boolean isWifi() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI;
    }


    private TelephonyManager telephonyManager;

    private TelephonyManager getTelephonyManger() {
        if (telephonyManager == null) {
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        }
        return telephonyManager;
    }
}
