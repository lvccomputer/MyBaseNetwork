package android.ncdev.network.di

import android.ncdev.network.service.TestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Singleton
    @Provides
    fun providesGirlService(retrofit: Retrofit): TestService =
        retrofit.create(TestService::class.java)
}