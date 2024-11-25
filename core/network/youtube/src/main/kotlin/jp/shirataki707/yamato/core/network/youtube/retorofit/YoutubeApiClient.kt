package jp.shirataki707.yamato.core.network.youtube.retorofit

import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import jp.shirataki707.yamato.core.network.BuildConfig
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiResponse
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for Youtube API
 */
private interface YoutubeApiService {
    @GET(value = "search")
    suspend fun getVideoResources(
        @Query("part") part: String,
        @Query("channelId") channelId: String?,
        @Query("maxResults") maxResults: Int?,
        @Query("order") order: String?,
        @Query("q") keyword: String?,
        @Query("type") type: String?,
    ): YoutubeSearchApiResponse
}

@Singleton
internal class YoutubeApiClient @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : YoutubeDataSource {

    private val youtubeApi = trace("YoutubeApiClient") {
        Retrofit.Builder()
            .baseUrl(BuildConfig.YoutubeBaseUrl)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(YoutubeApiService::class.java)
    }

    override suspend fun getVideoResources(request: YoutubeSearchApiRequest): YoutubeSearchApiResponse =
        youtubeApi.getVideoResources(
            part = request.part,
            channelId = request.channelId,
            maxResults = request.maxResults,
            order = request.order,
            keyword = request.keyword,
            type = request.type,
        )
}
