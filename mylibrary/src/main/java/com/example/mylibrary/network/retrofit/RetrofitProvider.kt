package com.example.mylibrary.network.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvider(
	private val baseUrl: String,
	private val isDebug: Boolean = false,
	private val connectTimeoutSec: Long = 15,
	private val readTimeoutSec: Long = 20,
	private val writeTimeoutSec: Long = 20,
	private val extraInterceptors: List<Interceptor> = emptyList()
) {

	private val okHttpClient: OkHttpClient by lazy {
		val builder = OkHttpClient.Builder()
			.connectTimeout(connectTimeoutSec, TimeUnit.SECONDS)
			.readTimeout(readTimeoutSec, TimeUnit.SECONDS)
			.writeTimeout(writeTimeoutSec, TimeUnit.SECONDS)

		if (isDebug) {
			val logging = HttpLoggingInterceptor().apply {
				level = HttpLoggingInterceptor.Level.BODY
			}
			builder.addInterceptor(logging)
		}

		extraInterceptors.forEach { builder.addInterceptor(it) }

		builder.build()
	}

	val retrofit: Retrofit by lazy {
		Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.build()
	}

	fun <T> create(service: Class<T>): T = retrofit.create(service)
}