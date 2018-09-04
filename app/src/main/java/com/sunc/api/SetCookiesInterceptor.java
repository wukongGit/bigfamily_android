package com.sunc.api;

import android.text.TextUtils;

import com.sunc.db.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:
 * Date: 2018-08-10 16:22
 * Author: suncheng
 */

public class SetCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //设置cookie
        Request.Builder builder = chain.request().newBuilder();

        String cookies = (String) SharedPreferencesHelper.getInstance().getData(SharedPreferencesHelper.CONFIG_COOKIE, "");
        if (!TextUtils.isEmpty(cookies)) {
            builder.addHeader("Cookie", cookies);
        }
        return chain.proceed(builder.build());
    }
}
