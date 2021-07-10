## GitHub Repository App

- Coroutine vs Thread

### Thread

Task단위 = Thread

- 각 작업에 Thread를 할당

- 각 Thread는 자체 Stack 메모리를 가지며, JVM Stack 영역 차지

Context Switching

- Blocking : Thread1이 Thread2의 결과가 나올 때 까지 기다려야 한다면 Thread1은 Blocking되어 사용하지 못함



### Coroutines

Task 단위 = Object(Coroutine)

- 각 작업에 Object(Coroutine)을 할당
- Coroutine은 객체를 담는 JVM Heap에 적재

Context Switching

- No Context Switching

- 코드를 통해 Switching 시점을 보장함

- Suspend is NonBlocking : Coroutine1이 Coroutine2의 결과가 나올 때 까지 기다려야 한다면 Coroutine1은 Suspend 되지만, Coroutine1을 수행하던 Thread는 유효함

  Coroutine2도 Coroutine1과 동일한 Thread에서 실행할 수 있음

### Suspend(일시 중단 함수)

- 앞에 suspend 키워드를 붙여서 함수를 구성하는 방법
- 람다를 구성하여 다른 일시 중단 함수를 호출함 ex) runBlocking{ ... }



### Coroutine Dispatcher

- 코루틴을 시작하거나 재개할 스레드를 결정하기 위한 도구
- 모든 Dispatcher는 CoroutineDispatcher 인터페이스를 구현해야 함



### Coroutine Builder

- async()
  - 결과가 예상되는 코루틴 시작에 사용(결과 반환)
  - 전역으로 예외 처리 가능
  - 결과, 예외 반환 가능한 Defered<T> 반환
- launch()
  - 결과를 반환하지 않는 코루틴 시작에 사용(결과 반환X)
  - 자체/ 자식 코루틴 실행을 취소할 수 있는 Job 반환
- runBlocking()
  - Blocking 코드를 일시 중지(Suspend) 가능한 코드로 연결하기 위한 용도
  - main함수나 Unit Test때 많이 사용됨
  - 코루틴의 실행이 끝날 때 까지 현재 스레드를 차단함



### runBlocking() vs async()

```kotlin
fun main() = runBlocking {
	val name = getFirstName()
	val lastName = getLastName()
	println("Hello, $name $lastName")
}

suspend fun getFirstName() : String{
	delay(1000)
	return "Hong"
}

suspend fun getLastName() : String{
	delay(1000)
	return "Eunho"
}
```

결과값은 Hello, Hong Eunho => 총 2초가 소요됨



```kotlin
fun main() = runBlocking {
	val name = Defered<String> = async { getFirstName() }
	val lastName = Defered<String> = async { getLastName() }
	println("Hello, ${name.await()} ${lastName.await()}")
}

suspend fun getFirstName() : String{
	delay(1000)
	return "Hong"
}

suspend fun getLastName() : String{
	delay(1000)
	return "Eunho"
}
```

결과값은 Hello, Hong Eunho => 총 1초가 소요됨



### Coroutine 사용하기

```kotlin
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9"
```

위의  코드를 build.gradle에 선언하여 셋팅을 함

