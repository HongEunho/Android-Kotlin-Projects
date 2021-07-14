# [DAY09] LiveData

앞의 ViewModel 문서의 Counter 예제에서 MainActivity 외에도 count를 조작하는 주체가 있다면?

=> 각각 마다 set작업을 해주어야 한다.

=> 이를 **LiveData**로 해결하자.

</br>

이 역시 lifecycle 패키지에 포함되며 생명주기를 고려한 Observer패턴

Observer패턴을 통해 데이터와 UI 상태가 항상 일치하게 유지

LiveData는 lifecycle의 객체를 받아 생명주기를 확인하고 destroy된 view는 제거하기 때문에

메모리 누수가 없음

</br>

최소 started 상태인 view로만 업데이트된 데이터를 전달하기 때문에 상태 체크하는 코드생략 가능

View가 다시 활성화되면 가장 최신의 값을 바로 전달 받음

</br>

View의 상태에 따라 실행하면 안되는 코드가 있을 수 있는데

LiveData는 활성화된 View에만 업데이트되기 때문에 현재 상태를 체크하는 코드가 불필요

</br>

**mutablelivedata**(변경가능) 은 private

**livedata**(변경불가) 는 public으로 외부에서 사용 가능

------

#### ViewModel의 추가 개념

</br>

ViewModel은 단순한 추상 클래스

mCleared => Scope가 완전히 종료되었는지 알려주는 플래그

mBagOfTags => extension에서 추가된 closable 객체를 저장하기 위해 추가된 해시맵

</br>

onCleared함수 => ViewModel의 유일한 protected 함수

- Clear되는 시점에 호출되며 오버라이드 해서 메모리 해제 등의 처리를 할 수 있음


- clear => Scope가 종료될 때 호출됨


- cleared 플래그를 업데이트 해주고 close 처리 및 onCleared를 호출해줌


</br>

ViewModel은 객체는 항상 ViewModelProvider를 통해 받아야 함

</br>

mFactory -> ViewModel 객체를 생성해주는 팩토리

mViewModelStore -> ViewModel 객체를 저장하는 객체

</br>

ViewModelStore는 단순히 ViewModel 객체를 저장하고 재사용하기 위한 HashMap wrapper

단, put을 할 때 map에서 제거되는 ViewModel 객체가 있으면 onCleared 호출

ViewModelStore 역시 scope가 종료될 때 호출될 clear를 제공함

ViewModel에 clear를 전달해주고 ViewModel 객체 참조를 해제함

</br>

ViewModelProvider.get으로 ViewModel객체를 가져올 때 ViewModel 클래스 네임을 키로 사용

키로 ViewModelStore에서 ViewModel 객체를 조회함

존재하지 않으면 Factory를 통해 새로운 객체 생성

</br>

생명주기에 맞게 ViewModel 객체를 사용하는 것은 ViewModelStoreOwner에 의해 가능

ViewModelStoreOwner는 ViewModelStore객체를 제공해주는 단순한 인터페이스

</br>

ViewModelStoreOwner를 구현하는 클래스가 구성 변경시 ViewModelStore 객체 유지,

완전히 종료되는 경우 clear 호출하는 책임을 가짐

</br>

Jetpack의 Activity, Fragment들이 모두 ViewModelStoreOwner를 구현하고 있으며

이들이 자신의 생명주기에 맞게 ViewModelStore 객체를 관리해 줌

</br>

ViewModel 라이브러리는 기존에 생명주기 때문에 작성해야 했던 보일러플레이트 코드를 줄여줌

하지만 Android 생명주기에 대한 이해없이 사용하면 메모리 누수는 쉽게 발생할 수 있음

</br>

ViewModel을 구현함에 있어 가장 피해야 하는 패턴은

ViewModel이 View, Activity, Fragment, Context등의 객체를 참조하는 것임

ViewModel의 생명주기가 Activity의 생명주기보다 더 길기 때문이다.

따라서, 메모리 누수가 발생한다거나 View가 destroy 된 이후에 코드가 실행될 수 있다.