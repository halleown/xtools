package com.joykade.xtools.network

import com.joykade.xtools.network.core.NetworkResult
import com.joykade.xtools.network.error.ApiErrorMapper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T> : Observer<NetworkResult<T>> {
	private var disposable: Disposable? = null

	override fun onSubscribe(d: Disposable) {
		disposable = d
		onStart()
	}

	override fun onNext(result: NetworkResult<T>) {
		when (result) {
			is NetworkResult.Loading -> onLoading()
			is NetworkResult.Success -> onSuccess(result.data)
			is NetworkResult.Error -> onError(result)
		}
	}

	override fun onError(e: Throwable) {
		onError(ApiErrorMapper().map(e))
	}

	override fun onComplete() {
		onFinish()
	}

	fun dispose() { disposable?.dispose() }

	open fun onStart() {}
	open fun onLoading() {}
	abstract fun onSuccess(data: T)
	open fun onError(error: NetworkResult.Error) {}
	open fun onFinish() {}
}