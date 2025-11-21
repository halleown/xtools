package com.joykade.xtools.network.model

data class ApiResponse<T>(
	val success: Boolean = false,
	val code: Int? = null,
	val message: String? = null,
	val data: T? = null
)