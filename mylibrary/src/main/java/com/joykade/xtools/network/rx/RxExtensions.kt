package com.joykade.xtools.network.rx

import com.joykade.xtools.network.core.NetworkResult
import com.joykade.xtools.network.core.SchedulersProvider
import com.joykade.xtools.network.error.ApiErrorMapper
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