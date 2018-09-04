package com.sunc.api

import com.sunc.app.BaseModel
import com.sunc.app.DataModel
import com.sunc.bean.LoginData
import com.sunc.bean.Member
import retrofit2.http.*
import rx.Observable

/**
 * Created by wing on 11/23/16.
 */
interface FamilyApi {

  @GET("rongyun/register")
  fun hello() : Observable<BaseModel>

  @FormUrlEncoded
  @POST("/authentication/form")
  fun login(@Field("username") username: String, @Field("password") password: String):Observable<DataModel<LoginData>>

  @FormUrlEncoded
  @POST("/user/register")
  fun register(@Field("username") username: String, @Field("password") password: String):Observable<DataModel<LoginData>>

  @GET("/user/members")
  fun members():Observable<DataModel<LoginData>>


}