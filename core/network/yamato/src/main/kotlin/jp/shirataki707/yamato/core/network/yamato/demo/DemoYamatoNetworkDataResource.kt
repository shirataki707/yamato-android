package jp.shirataki707.yamato.core.network.yamato.demo

import jp.shirataki707.yamato.core.common.network.DemoAssetManager
import jp.shirataki707.yamato.core.common.network.Dispatcher
import jp.shirataki707.yamato.core.common.network.JvmUnitTestDemoAssetManager
import jp.shirataki707.yamato.core.common.network.YamatoDispatchers
import jp.shirataki707.yamato.core.network.yamato.YamatoNetworkDataSource
import jp.shirataki707.yamato.core.network.yamato.model.NetworkMountain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class DemoYamatoNetworkDataResource @Inject constructor(
    @Dispatcher(YamatoDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoAssetManager = JvmUnitTestDemoAssetManager,
) : YamatoNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getMountains(): List<NetworkMountain> {
        return withContext(ioDispatcher) {
            assets.open(MOUNTAINS_ASSET).use(networkJson::decodeFromStream)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getMountain(id: Int): NetworkMountain {
        return withContext(ioDispatcher) {
            assets.open(MOUNTAIN_0001_ASSET).use(networkJson::decodeFromStream)
        }
    }

    companion object {
        private const val MOUNTAINS_ASSET = "mountains.json"
        private const val MOUNTAIN_0001_ASSET = "mountain_0001.json"
    }
}
