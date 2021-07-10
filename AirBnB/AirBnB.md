## AirBnB

- Naver Map API 사용

- ViewPager2

- FrameLayout

- CoordinatorLayout

- BottomSheetBehavior

- Retrofit

- Glide

  정사각형 이미지를 사이즈에 맞게 둥글게 CenterCrop 하는 법

  Glide.with... .transform(CenterCrop(), RoundedCorners(dpToPx(ImageView.context, 12))

  여기서 RoundedCorners( px ) 숫자부분은 px값이 들어가야 하기 때문에

  dp값을 넣게 되면 휴대폰마다 다른 사이즈가 적용이 됨

  따라서 dpToPx라는 함수를 만들어서 dp -> px로 변형



​		fun dpToPx(context: Context, dp: Int): Int { return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()}



### 에어비앤비 앱 기능

- Naver Map API를 이용해서 지도를 띄우고 활용할 수 있음
- Mock API에서 예약 가능 숙소 목록을 받아와서 지도에 표시함
- BottomSheetView를 활용하여 예약 가능 숙소 목록을 인터랙션하게 표시
- ViewPager2를 활용하여 현재 보고있는 숙소를 표시
- 숙소버튼을 눌러 현재 보고있는 숙소를 앱 외부로 공유