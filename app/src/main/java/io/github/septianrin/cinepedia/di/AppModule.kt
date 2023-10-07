package io.github.septianrin.cinepedia.di

import com.airbnb.lottie.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTMDBApiKey(): String{
        return BuildConfig.BUILD_TYPE
    }

}