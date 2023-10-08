package io.github.septianrin.cinepedia.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.septianrin.cinepedia.BuildConfig
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTMDBApiKey(): String {
        return BuildConfig.TMDB_TOKEN
    }

}