package android.telephony;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;

public class OplusOSTelephonyManager implements IOplusOSTelephony {
    private static final String TAG = "OplusOSTelephonyManager";
    private CarrierConfigManager mCarrierConfigManager;
    private Context mContext;
    private TelephonyManager mTelephonyManager;
    private static boolean isQcomGeminiSupport = false;
    private static boolean isMtkGeminiSupport = false;
    private static String vDescriptor = IOplusOSTelephony.OPLUS_SINGLE_CARD_DESCRIPTOR;
    private boolean mIsExpVersion = false;
    private boolean mIsDualLteSupported = true;

    public static OplusOSTelephonyManager getDefault(Context context) {
        OplusOSTelephonyManager sInstance = new OplusOSTelephonyManager(context);
        return sInstance;
    }

    public OplusOSTelephonyManager(Context context) {
        this.mContext = context;
        this.mTelephonyManager = TelephonyManager.from(context);
        initRemoteService();
    }

    private void initRemoteService() {
        isQcomGeminiSupport = true;
        isMtkGeminiSupport = false;
        vDescriptor = IOplusOSTelephony.OPLUS_SINGLE_CARD_DESCRIPTOR;
        this.mCarrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        PackageManager packageManager = this.mContext.getPackageManager();
        if (packageManager != null) {
            this.mIsExpVersion = packageManager.hasSystemFeature("oplus.version.exp");
        }
    }

    public void listenGemini(Context context, PhoneStateListener listener, int events, int slotID) {
        TelephonyManager telephonyManager = null;
        int[] subIds = SubscriptionManager.getSubId(slotID);
        if (subIds != null && subIds.length > 0) {
            telephonyManager = new TelephonyManager(context, subIds[0]);
        }
        if (telephonyManager != null) {
            telephonyManager.listen(listener, events);
        }
    }
}
