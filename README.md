# xtools
Android自用工具库，实现的功能有：

- [x] 使用Retrofit+RxJava封装网络请求
- [ ] 进制转换工具



### 集成步骤

**如何查看Gradle版本**

- 在`项目根目录/gradle/wrapper/gradle-wrapper.properties`
- 或者Ctrl+Alt+Shift+S打开Project Structure->Project->Gradle Version

#### 配置远程仓库

- Gradle Version < 7.0，在`build.gradle`添加

  ```groovy
  allprojects {
      repositories {
          // JitPack 远程仓库：https://jitpack.io
          maven { url 'https://jitpack.io' }
      }
  }
  ```

- Gradle Version >= 7.0，在`settings.gradle`添加

  ```groovy
  dependencyResolutionManagement {
      repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
      repositories {
          mavenCentral()
          // JitPack 远程仓库：https://jitpack.io
          maven { url 'https://jitpack.io' }
      }
  }
  ```

#### 引入依赖

```groovy
dependencies {
    implementation 'com.github.halleown:xtools:1.0.0'
}
```



### 使用网络库

###### 调用getInfo接口

声明权限


```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

Kotlin使用示例：

```kotlin
val provider = RetrofitProvider(
    "http://192.168.3.167:4523/m1/6823482-6537445-default/", true
)
val api = provider.create(SampleApi::class.java)

api.getInfo1<User>()
.doOnNext { r -> Log.d(TAG, "resp: success=${r.success}, code=${r.code}, msg=${r.message}, data=${r.data}") }
.applyIoToMainSchedulers().compose(apiResponseToNetworkResult())
.subscribe(object : BaseObserver<User>() {
    override fun onStart() {
    }

    override fun onLoading() {
    }

    override fun onSuccess(data: User) {
    }

    override fun onError(error: NetworkResult.Error) {
        // success=false 或者其他业务错误回调
    }

    override fun onFinish() {

    }
})
```



接口数据结构：

```json
{
   "success": true,
   "code": 0,
   "message": "ok",
   "data": {
      "id": 1,
      "name": "jake",
      "age": 18,
      "desc": "he likes to eat vegetables"
   }
}
```





