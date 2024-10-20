package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.database.dao.MountainDao
import jp.shirataki707.yamato.core.database.model.MountainEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MountainRepositoryImpl @Inject constructor(
    private val mountainDao: MountainDao,
) : MountainRepository {
    override fun getMountains(): Flow<List<MountainEntity>> {
        return mountainDao.getMountainEntities()
    }

    override fun getMountain(id: Int): Flow<MountainEntity> {
        TODO("Not yet implemented")
    }
}