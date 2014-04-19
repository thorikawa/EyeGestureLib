package com.google.android.glass.eye;

import android.os.Parcel;
import android.os.Parcelable;

public enum EyeGesture implements Parcelable {
    BLINK, DOFF, DON, DOUBLE_BLINK, DOUBLE_WINK, LOOK_AT_SCREEN, LOOK_AWAY_FROM_SCREEN, WINK;

    public int getId() {
        return -1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1) {
    }
}
