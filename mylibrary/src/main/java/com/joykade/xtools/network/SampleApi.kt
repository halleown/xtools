package com.joykade.xtools.network

import com.joykade.xtools.network.model.ApiResponse
import com.joykade.xtools.network.model.User
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import retrofit2.http.GET

interface SampleApi {
	@GET("userInfo")
	fun getUserInfo(): Observable<ApiResponse<User>>

	// retrofit不可以返回泛型，利用JsonElement中转
	@GET("info")
	fun getInfo(): Observable<ApiResponse<JsonElement>>
}


inline fun <reified T> Observable<ApiResponse<JsonElement>>.mapInfo(gson: Gson): Observable<ApiResponse<T>> =
	map { r ->
		val parsed: T = gson.fromJson(r.data, object : TypeToken<T>() {}.type)
		ApiResponse(r.success, r.code, r.message, parsed)
	}

inline fun <reified T> SampleApi.getInfo1(): Observable<ApiResponse<T>> =
	getInfo().mapInfo<T>(Gson())
