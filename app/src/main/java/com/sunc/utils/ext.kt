package com.sunc.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by wing on 11/23/16.
 */
fun Context.toast(msg:String,length:Int = Toast.LENGTH_SHORT){
  Toast.makeText(this, msg, length).show()
}
