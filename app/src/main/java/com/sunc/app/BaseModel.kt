package com.sunc.app

/**
 * Created by Administrator on 2018/6/25.
 */
data class BaseModel(val code: Int, val message: String) {
    fun isSuccess() = code == 0
}