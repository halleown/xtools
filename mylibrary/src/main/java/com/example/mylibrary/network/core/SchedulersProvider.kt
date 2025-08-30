package com.example.mylibrary.network.core

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulersProvider {
	fun io(): Scheduler = Schedulers.io()
	fun computation(): Scheduler = Schedulers.computation()
	fun main(): Scheduler = AndroidSchedulers.mainThread()
}