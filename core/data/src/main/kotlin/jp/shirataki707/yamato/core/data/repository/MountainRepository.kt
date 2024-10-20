package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.model.data.Mountain
import kotlinx.coroutines.flow.Flow

interface MountainRepository {
    /*
     * Gets the available mountains as a stream
     */
    fun getMountains(): Flow<List<Mountain>>

    /*
     * Gets data for a specific mountain
     */
    fun getMountain(id: Int): Flow<Mountain>
}
