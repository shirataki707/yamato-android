package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.common.network.Dispatcher
import jp.shirataki707.yamato.core.common.network.YamatoDispatchers
import jp.shirataki707.yamato.core.model.data.Mountain
import jp.shirataki707.yamato.core.network.yamato.YamatoNetworkDataSource
import jp.shirataki707.yamato.core.network.yamato.model.asExternalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MountainRepository {
    suspend fun getMountains(): List<Mountain>

    suspend fun getMountain(id: Int): Mountain
}

internal class MountainRepositoryImpl @Inject constructor(
    @Dispatcher(YamatoDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val yamatoNetworkDataSource: YamatoNetworkDataSource,
) : MountainRepository {

    override suspend fun getMountains(): List<Mountain> {
        return withContext(ioDispatcher) {
            yamatoNetworkDataSource.getMountains().map { it.asExternalModel() }
        }
    }

    override suspend fun getMountain(id: Int): Mountain {
        return yamatoNetworkDataSource.getMountain(id).asExternalModel()
    }
}
