package com.sunc.bigfamily

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.rong.imkit.RongIM

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RongIM.getInstance().startPrivateChat(this, "9527", "标题");
    }
}
