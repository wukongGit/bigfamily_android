package com.sunc.app;

import android.content.Context;
import android.util.Log;

import io.rong.imlib.RongIMClient;

/**
 * Description:
 * Date: 2018-09-19 10:37
 * Author: suncheng
 */

public class SealAppContext {
    private final static String TAG = "SealAppContext";

    private Context mContext;
    private static SealAppContext mRongCloudInstance;

    public SealAppContext(Context mContext) {
        this.mContext = mContext;
    }

    public static void init(Context context) {
        if (mRongCloudInstance == null) {
            synchronized (SealAppContext.class) {
                if (mRongCloudInstance == null) {
                    mRongCloudInstance = new SealAppContext(context);
                }
            }
        }
    }

    public static SealAppContext getInstance() {
        return mRongCloudInstance;
    }

    public Context getContext() {
        return mContext;
    }

    public RongIMClient.ConnectCallback getConnectCallback() {
        RongIMClient.ConnectCallback connectCallback = new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "ConnectCallback connect onTokenIncorrect");

            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "ConnectCallback connect onSuccess");
            }

            @Override
            public void onError(final RongIMClient.ErrorCode e) {
                Log.d(TAG, "ConnectCallback connect onError-ErrorCode=" + e);
            }
        };
        return connectCallback;
    }
}
