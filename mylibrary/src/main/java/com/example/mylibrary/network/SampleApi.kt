package com.example.mylibrary.network

import com.example.mylibrary.network.model.ApiResponse
import com.example.mylibrary.network.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface SampleApi {
	@GET("userInfo")
	fun getUserInfo(): Observable<ApiResponse<User>>
}