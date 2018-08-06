package com.sunc.api

import com.sunc.app.BaseModel
import retrofit2.http.*
import rx.Observable

/**
 * Created by wing on 11/23/16.
 */
interface FamilyApi {

  @FormUrlEncoded
  @POST("login")
  fun login(@Field("username") username: String, @Field("password") password: String):Observable<BaseModel>


}