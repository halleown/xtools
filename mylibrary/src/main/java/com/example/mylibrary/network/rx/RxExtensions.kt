package com.example.mylibrary.network.rx

import com.example.mylibrary.network.core.NetworkResult
import com.example.mylibrary.network.core.SchedulersProvider
import com.example.mylibrary.network.error.ApiErrorMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

fun <T> Observable<T>.applyIoToMainSchedulers(): Observable<T> {
	return this.subscribeOn(SchedulersProvider.io())
		.observeOn(SchedulersProvider.main())
}

fun <T> toNetworkResult(): ObservableTransformer<T, NetworkResult<T>> {
	return ObservableTransformer { upstream: Observable<T> ->
		upstream
			.map<NetworkResult<T>> { data -> NetworkResult.Success(data) }
			.onErrorReturn { throwable -> ApiErrorMapper().map(throwable) }
			.startWith(NetworkResult.Loading)
	}
}