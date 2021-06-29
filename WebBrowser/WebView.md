## WebView

- http도 기본 브라우저가 아닌 만든 브라우저에서 열리게 하려면

  webView.webViewClient = WebViewClient 필요

- 다양한 기능(자바스크립트로 구현된) 사용을 위해

  webView.settings.javaScriptEnabled = true

- editText. setOnEditorActionListener

  IME_ACTION_DONE(imeOptions) => OK눌렀을 때 키보드 자동으로 닫기

