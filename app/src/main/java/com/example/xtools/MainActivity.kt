package com.example.xtools

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.halleown.xtools.XToolsConfig
import com.halleown.xtools.network.BaseObserver
import com.halleown.xtools.network.SampleApi
import com.halleown.xtools.network.core.NetworkResult
import com.halleown.xtools.network.getUserInfo1
import com.halleown.xtools.network.model.User
import com.halleown.xtools.network.retrofit.RetrofitProvider
import com.halleown.xtools.network.rx.apiResponseToNetworkResult
import com.halleown.xtools.network.rx.applyIoToMainSchedulers
import com.halleown.xtools.utils.LogUtil

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var tvView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvView = findViewById(R.id.tv_view)

        XToolsConfig.init(true)
        getUserInfo(1)
    }


    private fun getUserInfo(userId: Int) {
        val provider = RetrofitProvider("http://192.168.3.178:3000/")
        val api = provider.create(SampleApi::class.java)

        api.getUserInfo1<User>(userId)
            .doOnNext { r ->
                // io线程
                LogUtil.e("$r")
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
                    LogUtil.e("$data")
                    tvView.text = data.toString()
                }

                override fun onError(error: NetworkResult.Error) {
                    // success=false 或者其他业务错误回调
                    LogUtil.e("$error")
                }

                override fun onFinish() {
                    Log.d(TAG, "onFinish: ")
                }
            })
    }
}