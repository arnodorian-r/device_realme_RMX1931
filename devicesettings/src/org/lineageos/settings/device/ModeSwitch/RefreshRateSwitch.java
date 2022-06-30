/*
* Copyright (C) 2020 The LineageOS Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.lineageos.settings.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceManager;

import org.lineageos.settings.device.DeviceSettings;

public class RefreshRateSwitch implements OnPreferenceChangeListener {

    public static final String SETTINGS_KEY = DeviceSettings.KEY_SETTINGS_PREFIX + DeviceSettings.KEY_REFRESH_RATE;

    private static final IBinder SurfaceFlinger = ServiceManager.getService("SurfaceFlinger");
    public static int setState;

    private Context mContext;

    public RefreshRateSwitch(Context context) {
        mContext = context;
    }

    public static void setForcedRefreshRate(int value) {
        Parcel Info = Parcel.obtain();
        Info.writeInterfaceToken("android.ui.ISurfaceComposer");
        Info.writeInt(value);
        try {
            SurfaceFlinger.transact(1035, Info, null, 0);
        } catch (RemoteException e) {
            Log.e("RealmeSettings", e.toString());
        } finally {
            Info.recycle();
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Boolean enabled = (Boolean) newValue;
        if (preference == DeviceSettings.mRefreshRate90 && enabled) {
            setState = 1;
        } else if (preference == DeviceSettings.mRefreshRate60 && enabled) {
            setState = 0;
        }

        switch (setState) {
            case 0:
                Settings.System.putFloat(mContext.getContentResolver(), "PEAK_REFRESH_RATE".toLowerCase(), 60f);
                Settings.System.putFloat(mContext.getContentResolver(), "MIN_REFRESH_RATE".toLowerCase(), 60f);
                setForcedRefreshRate(0);
                break;
            case 1:
                Settings.System.putFloat(mContext.getContentResolver(), "PEAK_REFRESH_RATE".toLowerCase(), 90f);
                Settings.System.putFloat(mContext.getContentResolver(), "MIN_REFRESH_RATE".toLowerCase(), 90f);
                setForcedRefreshRate(1);
                break;
        }
        return true;
    }
}
