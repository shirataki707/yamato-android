package jp.shirataki707.yamato.core.network.youtube.retorofit

import jp.shirataki707.yamato.core.network.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for Youtube API
 */
private interface YoutubeApi {

}

private const val YoutubeBaseUrl = "https://www.googleapis.com/youtube/v3/"
private const val YoutubeApiKey = BuildConfig.YoutubeApiKey

@Singleton
internal class RetrofitYoutube @Inject constructor() {

}
