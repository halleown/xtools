package com.halleown.xtools.network.model

data class ApiResponse<T>(
	val code: Int? = null,
	val message: String? = null,
	val data: T? = null
)