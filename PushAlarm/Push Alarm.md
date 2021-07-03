## Push Alarm

- FirebaseMessagingService() 사용

  gradle에 아래 추가 해주고

  ```
  implementation platform('com.google.firebase:firebase-bom:28.2.0')
  implementation 'com.google.firebase:firebase-analytics-ktx'
  implementation 'com.google.firebase:firebase-messaging-ktx'
  ```

  plugins에 id 'com.google.gms.google-services' 추가하고

  manifest에 아래 추가 ( 위 내용들은 firebase Cloudmessaging공식 문서에 나와있음 )

  ```
  <service android:name=".MyFirebaseMessagingService"
      android:exported="false">
      <intent-filter>
          <action android:name="com.google.firebase.MESSAGING_EVENT"/>
      </intent-filter>
  </service>
  ```

- onNewToken

- onMessageReceived

- Run -> Attach To Debuger 로 진행 사항 확인 가능

- Notification Channel (오레오 이상 버전부터는 필수)

- 알림만들기 ( NotificationCompat.Builder(context, channelID) )

- 코틀린 생성자(val title: String, val id: Int)

  자바보다 간편하게 생성자 생성

- 푸쉬알림 3타입 ( 일반, 확장형, 커스텀)

- 인텐트에 대한 내용은 안드 공식문서의 활동->작업 및 백 스택에 자세하게 나와있음

- onNewIntent 는 앱 실행 중 알림을 눌렀을때 앱을 새로고침해주는 override 함수