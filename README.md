# xtools
Android工具库



## 使用

Step1.在项目根目录的`settings.gradle`添加

```groovy
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
            // 添加此行
			maven { url 'https://jitpack.io' }
		}
	}
```

Step2.引入jar包

```groovy
	dependencies {
	        implementation 'com.github.renoly:xtools:1.0.1'
	}
```



## 网络库

1. 声明权限


```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

2. 使用示例：

```kotlin
val provider = RetrofitProvider(
   baseUrl = "http://127.0.0.1:4523/m1/6823482-6537445-default/",
   isDebug = true
)
val api = provider.create(SampleApi::class.java)


api.getInfo()
   .applyIoToMainSchedulers()
   .compose(apiResponseToNetworkResult())
   .subscribe(object : BaseObserver<Info>() {
      override fun onStart() {
         Log.d(TAG, "onStart: ")
      }

      override fun onLoading() {
         Log.d(TAG, "onLoading: ")
      }

      override fun onSuccess(data: Info) {
         Log.d(TAG, "onSuccess: ${data}")
         runOnUiThread {
            test.text = data.name
         }
      }

      override fun onError(error: NetworkResult.Error) {
         // 这里会收到 success=false 的业务错误
         // 可读取 error.code / error.message
         Log.e(TAG, "onError: ${error}")
      }

      override fun onFinish() {
         Log.d(TAG, "onFinish: ")
      }

   })
```


网络接口会返回如下数据：
```json
{
   "success": true,
   "code": 0,
   "message": "ok",
   "data": "aaaa"
}
```





