package jp.shirataki707.yamato.core.network.yamato.retrofit

import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import jp.shirataki707.yamato.core.network.yamato.BuildConfig
import jp.shirataki707.yamato.core.network.yamato.YamatoNetworkDataSource
import jp.shirataki707.yamato.core.network.yamato.model.NetworkMountain
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

/*
 * Retrofit API declaration for Yamato Network API
 */
private interface RetrofitYamatoNetworkApi {
    @GET("mountains/{id}")
    suspend fun getMountain(@Path("id") id: Int): NetworkMountain

    @GET("mountains")
    suspend fun getMountains(): List<NetworkMountain>
}

@Singleton
internal class RetrofitYamatoNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : YamatoNetworkDataSource {

    private val networkApi = trace("RetrofitYamatoNetwork") {
        Retrofit.Builder()
            .baseUrl(BuildConfig.YamatoBaseUrl)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitYamatoNetworkApi::class.java)
    }

    override suspend fun getMountains(): List<NetworkMountain> {
        return networkApi.getMountains()
    }

    override suspend fun getMountain(id: Int): NetworkMountain {
        return networkApi.getMountain(id)
    }
}
