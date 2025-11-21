package com.example.xtools

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.joykade.xtools.network.BaseObserver
import com.joykade.xtools.network.SampleApi
import com.joykade.xtools.network.core.NetworkResult
import com.joykade.xtools.network.getInfo1
import com.joykade.xtools.network.model.User
import com.joykade.xtools.network.retrofit.RetrofitProvider
import com.joykade.xtools.network.rx.apiResponseToNetworkResult
import com.joykade.xtools.network.rx.applyIoToMainSchedulers

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvView = findViewById<TextView>(R.id.tv_view)

        val provider = RetrofitProvider(
            "http://192.168.3.167:4523/m1/6823482-6537445-default/", true
        )
        val api = provider.create(SampleApi::class.java)

        api.getInfo1<User>()
            .doOnNext { r ->
                Log.d(
                    TAG,
                    "resp: success=${r.success}, code=${r.code}, msg=${r.message}, data=${r.data}"
                )
            }
            .applyIoToMainSchedulers().compose(apiResponseToNetworkResult())
            .subscribe(object : BaseObserver<User>() {
                override fun onStart() {
                    Log.d(TAG, "onStart: ")
                }

                override fun onLoading() {
                    Log.d(TAG, "onLoading: ")
                }

                override fun onSuccess(data: User) {
                    Log.d(TAG, "${Thread.currentThread().name}=======onSuccess: ${data}")
                    tvView.text = data.toString()
                }

                override fun onError(error: NetworkResult.Error) {
                    // success=false 或者其他业务错误回调
                    Log.e(TAG, "${Thread.currentThread().name}=======onError: ${error}")
                }

                override fun onFinish() {
                    Log.d(TAG, "onFinish: ")
                }
            })

    }
}