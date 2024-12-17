package jp.shirataki707.yamato.core.network.yamato

import jp.shirataki707.yamato.core.network.yamato.model.NetworkMountain

/*
 * Interface representing network calls to the Yamato backend
 */
interface YamatoNetworkDataSource {
    suspend fun getMountain(id: Int): NetworkMountain
    suspend fun getMountains(): List<NetworkMountain>
}
