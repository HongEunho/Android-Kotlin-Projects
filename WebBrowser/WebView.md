## WebView

- http도 기본 브라우저가 아닌 만든 브라우저에서 열리게 하려면

  webView.webViewClient = WebViewClient 필요

- http가 보안상으로 안열릴 때 열리게 하려면

  manifest에서 usesCleartextTraffic = "true"

- 다양한 기능(자바스크립트로 구현된) 사용을 위해

  webView.settings.javaScriptEnabled = true

- editText. setOnEditorActionListener

  IME_ACTION_DONE(imeOptions) => OK눌렀을 때 키보드 자동으로 닫기
  
- WebViewClient vs WebChromeClient

  WebViewClient는 주로 컨텐츠 로딩에 관련된 것들

  WebChromeClient 좀 더 브라우적인 차원. 자바스크립트의 이벤트 등. (컨텐츠 받아오기)

- ```xml
  <item name="android:windowLightStatusBar">true</item>
  상태바 투명하게 설정 ( 아이콘 안묻히게 )
  ```

