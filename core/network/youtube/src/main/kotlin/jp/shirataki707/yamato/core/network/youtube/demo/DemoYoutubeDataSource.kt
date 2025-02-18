package jp.shirataki707.yamato.core.network.youtube.demo

import jp.shirataki707.yamato.core.common.network.DemoAssetManager
import jp.shirataki707.yamato.core.common.network.Dispatcher
import jp.shirataki707.yamato.core.common.network.JvmUnitTestDemoAssetManager
import jp.shirataki707.yamato.core.common.network.YamatoDispatchers.IO
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okio.use
import javax.inject.Inject

class DemoYoutubeDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoAssetManager = JvmUnitTestDemoAssetManager,
) : YoutubeDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getVideoResources(request: YoutubeSearchApiRequest): YoutubeSearchApiResponse =
        withContext(ioDispatcher) {
            assets.open(YOUTUBE_SEARCH_RESULT_ASSET).use(networkJson::decodeFromStream)
        }

    companion object {
        private const val YOUTUBE_SEARCH_RESULT_ASSET = "youtubeSearchResults.json"
    }
}
