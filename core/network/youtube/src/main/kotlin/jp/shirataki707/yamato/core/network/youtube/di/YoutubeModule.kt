package jp.shirataki707.yamato.core.network.youtube.di

import androidx.tracing.trace
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.common.network.ApiKeyInterceptor
import jp.shirataki707.yamato.core.network.youtube.BuildConfig
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object YoutubeModule {

    @Provides
    @Singleton
    @Named("Youtube")
    fun okHttpCallFactory(): Call.Factory = trace("YoutubeOkHttpClient") {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(BuildConfig.YoutubeApiKey))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                },
            )
            .build()
    }
}
