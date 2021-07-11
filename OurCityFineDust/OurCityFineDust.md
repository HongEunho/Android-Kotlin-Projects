## OurCityFineDust

## Coroutine

코루틴을 사용할 때 전체적인 구조는 다음과 같다.

```kotlin
val scope = CoroutineScope(CoroutineContext)
val job = scope.CoroutineBuilder {
	...
}
```

CoroutineContext에는 Dispatchers.Main / Dispatcher.IO / Dispatchers.Default ... 가 있는데

Main에는 UI 관련 작업이 모여있는 쓰레드풀이며

IO에는 데이터를 읽고 쓰는 작업이 모여있는 쓰레드풀이며

Default는 기본 쓰레드풀로, CPU 사용량이 많은 작업에 적합한 쓰레드풀이다.



CoroutineBuilder에는 async{} , launch{}, runBlocking{} 이 있다.



### 오픈API를 사용할 때 편리하게 json응답을 모델로 만드는법

plugins에서 json to kotlin 설치 후

추가 할 패키지로 가서 우클릭 -> new file-> kotlin data class file ..json 열고

응답 json 복붙



RetrofitCreate할 때, 코틀린에서는 Builder에서 바로 create()할 수 있음



### Retrofit(Rest API) 사용시 필수참조

- ...ApiService interface( 여기서 get, post같은 것들 수행 )
- Repository 혹은 RetrofitUtil Object ( 여기서 Retrofit.builder )
- BaseUrl을 저장할 Url Object
- 각각의 응답형식에 맞는 Data파일

