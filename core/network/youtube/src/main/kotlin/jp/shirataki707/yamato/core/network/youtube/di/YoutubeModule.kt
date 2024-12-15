package jp.shirataki707.yamato.core.network.youtube.di

import android.content.Context
import androidx.tracing.trace
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.common.network.ApiKeyInterceptor
import jp.shirataki707.yamato.core.network.youtube.BuildConfig
import jp.shirataki707.yamato.core.network.youtube.demo.DemoAssetManager
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object YoutubeModule {

    @Provides
    @Singleton
    fun providesDemoAssetManager(
        @ApplicationContext context: Context,
    ): DemoAssetManager = DemoAssetManager(context.assets::open)

    @Provides
    @Singleton
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
