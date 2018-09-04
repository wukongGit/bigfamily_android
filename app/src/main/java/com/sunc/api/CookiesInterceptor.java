package com.sunc.api;

import android.text.TextUtils;
import android.util.Log;

import com.sunc.db.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Description:
 * Date: 2018-08-10 16:22
 * Author: suncheng
 */

public class CookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.from(originalResponse.headers("Set-Cookie"))
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            cookieBuffer.append(cookie).append(";");
                        }
                    });

            if (!TextUtils.isEmpty(cookieBuffer.toString())) {
                Log.e("CookiesInterceptor", cookieBuffer.toString());
                SharedPreferencesHelper.getInstance().saveData(SharedPreferencesHelper.CONFIG_COOKIE, cookieBuffer.toString());
            }
        }
        return originalResponse;
    }
}
