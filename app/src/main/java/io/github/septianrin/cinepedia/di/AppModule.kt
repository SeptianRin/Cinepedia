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
        return "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4NTMzNjQ4OWVlNjNlZTg0MThlNDYxYjQyN2FjZTBjZSIsInN1YiI6IjYwYjRlYmNjZGQ5MjZhMDA2ZDg5NjEzMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lCeAYgoYtXG-Z6yTqJPXa6EzrxwSr_NOtKOQE12ON8k"
    }

}