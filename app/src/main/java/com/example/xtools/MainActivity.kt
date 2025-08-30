package com.example.xtools

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylibrary.network.BaseObserver
import com.example.mylibrary.network.SampleApi
import com.example.mylibrary.network.core.NetworkResult
import com.example.mylibrary.network.model.User
import com.example.mylibrary.network.retrofit.RetrofitProvider
import com.example.mylibrary.network.rx.apiResponseToNetworkResult
import com.example.mylibrary.network.rx.applyIoToMainSchedulers
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val provider = RetrofitProvider(
            baseUrl = "http://192.168.3.13:4523/m1/6823482-6537445-default/", isDebug = true
        )
        val api = provider.create(SampleApi::class.java)

        api.getUserInfo().applyIoToMainSchedulers().compose(apiResponseToNetworkResult())
            .subscribe(object : BaseObserver<User>() {
                override fun onStart() {
                    Log.d(TAG, "onStart: ")
                }

                override fun onLoading() {
                    Log.d(TAG, "onLoading: ")
                }

                override fun onSuccess(data: User) {
                    Log.d(TAG, "onSuccess: ${data}")
                }

                override fun onError(error: NetworkResult.Error) {
                    // success=false 或者其他业务错误回调
                    Log.e(TAG, "onError: ${error}")
                }

                override fun onFinish() {
                    Log.d(TAG, "onFinish: ")
                }

            })

    }
}