package com.example.daggergallery.di

import android.app.Application
import android.content.Context
import com.example.daggergallery.GalleryActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindsContext(application: Application): Context

    // AndroidInjector를 사용하기 때문에 더 이상 서브 컴포넌트를 정의할 필요가 없다.
    // 따라서 GalleryComponent 파일을 지워도 된다.
    // 대신 @ContributesAndroidInjector를 사용하여 서브 컴포넌트 정의를 대신함.

    // Dagger는 @ContributesAndroidInjector로부터 얻은 메타데이터를 통해
    // 서브 컴포넌트를 컴파일 타임에 생성
    // 반드시 상위 컴포넌트의 모듈 내 추상 메소드로 정의

    @ActivityScope
    @ContributesAndroidInjector(modules = [GalleryModule::class])
    abstract fun galleryActivity(): GalleryActivity
}