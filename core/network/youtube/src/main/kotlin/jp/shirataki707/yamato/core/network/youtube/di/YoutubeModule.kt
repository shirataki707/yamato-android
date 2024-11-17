package jp.shirataki707.yamato.core.network.youtube.di

import androidx.tracing.trace
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.network.BuildConfig
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.retorofit.YoutubeApiClient
import jp.shirataki707.yamato.core.network.youtube.retorofit.YoutubeApiKeyInterceptor
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
    fun provideNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = trace("YoutubeOkHttpClient") {
        OkHttpClient.Builder()
            .addInterceptor(YoutubeApiKeyInterceptor(BuildConfig.YoutubeApiKey))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                },
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideYoutubeDataSource(
        networkJson: Json,
        okhttpCallFactory: dagger.Lazy<Call.Factory>
    ): YoutubeDataSource = YoutubeApiClient(networkJson, okhttpCallFactory)
}
