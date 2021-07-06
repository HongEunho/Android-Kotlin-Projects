## BookReview

- RecyclerView ( RecyclerViewAdapter -> ListAdapter + diffUtil )

  adapter.submitList

  

- View Binding

  build.gradle에 viewBinding{enabled = true} 명시해줄 것

  Binding의 이름은 layout파일의 _ 를 제외하면 됨.

  ex) activity_main.xml => ActivityMainBinding / item_book.xml => ItemBookBinding

  RecyclerView 뿐만 아니라 MainActivity도 ViewBinding을 통해 setContentView 함

  ( binding = ActivityMainBinding.inflate(layoutInflater) )

  ( setContentView(binding.root) )

  

- Retrofit

  ```
  implementation 'com.squareup.retrofit2:retrofit:2.9.0'
  implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
  ```

  build.gradle에 선언함으로써 retrofit 셋팅을 함

  

- Glide

- Android Room

- Open API



#### 기능

인터파크 Open API를 통해 베스트셀러 정보를 가져와서 화면에 그림

인터파크 Open API를 통해 검색어에 해당하는 책 목록을 가져와서 화면에 그림

Local DB를 이용하여 검색 기록을 저장하고 삭제함

Local DB를 이용하여 개인 리뷰를 저장함