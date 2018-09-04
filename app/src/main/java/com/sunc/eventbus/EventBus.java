package com.sunc.eventbus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Description:
 * Date: 2018-08-29 15:14
 * Author: suncheng
 */

public class EventBus {
    private static final Bus sAndroidBus = new AndroidBus();

    public static Bus bus() {
        return sAndroidBus;
    }

    private static class AndroidBus extends Bus {
        private final Handler mMainHandler;

        public AndroidBus() {
            super(ThreadEnforcer.ANY);
            mMainHandler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void post(final Object event) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.post(event);
            } else {
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        AndroidBus.super.post(event);
                    }
                });
            }
        }
    }
}
