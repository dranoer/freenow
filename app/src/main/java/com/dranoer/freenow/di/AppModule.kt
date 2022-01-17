package com.dranoer.freenow.di

import android.content.Context
import androidx.room.Room
import com.dranoer.freenow.BuildConfig
import com.dranoer.freenow.Constants
import com.dranoer.freenow.Constants.DATABASE_NAME
import com.dranoer.freenow.Constants.TIME_OUT
import com.dranoer.freenow.data.local.VehicleDatabase
import com.dranoer.freenow.data.remote.NetworkDataSource
import com.dranoer.freenow.data.remote.WebService
import com.dranoer.freenow.domain.VehicleRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): WebService {

        val gson = GsonBuilder().setLenient().create()

        val httpLogger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLogger)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(remoteSource: NetworkDataSource) = VehicleRepository(remoteSource)

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        VehicleDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(db: VehicleDatabase) = db.vehicleDao()
}