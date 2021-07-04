## Today's saying ( 오늘의 명언 )

- Firebase의 Remote Config사용(코드 변경없이 파이어베이스에서 컨트롤)

- 12시간에 한번 변경이 반영되는 것을 변경하기 위해서는 ( 개발자 용으로 )

  ```
  remoteConfigSettings {
      minimumFetchIntervalInSeconds = 0
  }
  ```



- textView의 ellipsize = "end" 는 글자가 너무 길어질 경우 마지막 부분 ... 표시
- 무한 스크롤 (adapter에서 getItemSize를 Int.MAXVALUE 로 주고 % 처리)
- 첫 페이지가 나타나게 되면 왼쪽으로 안넘어가니까 setCurrentItem으로 설정
- 화면이 넘어갈 때 잔상 효과를 주기 위해 viewPager.setPageTransformer

