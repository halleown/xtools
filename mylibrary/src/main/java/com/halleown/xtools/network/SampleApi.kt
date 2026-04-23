package com.halleown.xtools.network

import com.halleown.xtools.network.model.ApiResponse
import com.halleown.xtools.network.model.User
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SampleApi {

    // retrofit不可以返回泛型，需利用JsonElement中转
    @GET("/api/users/{userId}")
    fun getUserInfo(@Path("userId") userId: Int): Observable<ApiResponse<JsonElement>>

}

inline fun <reified T> Observable<ApiResponse<JsonElement>>.mapInfo(gson: Gson): Observable<ApiResponse<T>> =
    map { r ->
        val parseData: T = gson.fromJson(r.data, object : TypeToken<T>() {}.type)
        ApiResponse(r.code, r.message, parseData)
    }

inline fun <reified T> SampleApi.getUserInfo1(userId: Int): Observable<ApiResponse<T>> =
    getUserInfo(userId).mapInfo<T>(Gson())
