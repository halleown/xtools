package com.example.mylibrary.network.rx

import com.example.mylibrary.network.core.NetworkResult
import com.example.mylibrary.network.model.ApiResponse
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * Maps ApiResponse<T> to T on success, throws on business failure.
 */
fun <T> unwrapApiResponse(): ObservableTransformer<ApiResponse<T>, T> {
	return ObservableTransformer { upstream: Observable<ApiResponse<T>> ->
		upstream.flatMap { resp ->
			if (resp.success && resp.data != null) {
				Observable.just(resp.data)
			} else {
				val error = NetworkResult.Error(
					code = resp.code,
					message = resp.message ?: "Business failure"
				)
				Observable.error(RuntimeException(error.message ?: "Business failure"))
			}
		}
	}
}

/**
 * Compose helper: ApiResponse<T> -> NetworkResult<T>
 */
fun <T> apiResponseToNetworkResult(): ObservableTransformer<ApiResponse<T>, NetworkResult<T>> {
	return ObservableTransformer { upstream ->
		upstream
			.compose(unwrapApiResponse())
			.compose(toNetworkResult())
	}
}