package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.database.dao.MountainDao
import jp.shirataki707.yamato.core.database.model.MountainEntity
import jp.shirataki707.yamato.core.database.model.asExternalModel
import jp.shirataki707.yamato.core.model.data.Mountain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

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

internal class MountainRepositoryImpl @Inject constructor(
    private val mountainDao: MountainDao,
) : MountainRepository {

    override fun getMountains(): Flow<List<Mountain>> =
        mountainDao.getMountainEntities()
            .map { it.map(MountainEntity::asExternalModel) }

    override fun getMountain(id: Int): Flow<Mountain> =
        mountainDao.getMountainEntity(id).map { it.asExternalModel() }
}
