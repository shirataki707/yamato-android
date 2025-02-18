package jp.shirataki707.yamato.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import jp.shirataki707.yamato.core.database.model.MountainEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [MountainEntity] access
 */
@Dao
interface MountainDao {
    @Query(
        value = """
        SELECT * FROM mountains
        WHERE id = :mountainId
    """,
    )
    fun getMountainEntity(mountainId: Int): Flow<MountainEntity>

    @Query("SELECT * FROM mountains")
    fun getMountainEntities(): Flow<List<MountainEntity>>

    @Insert
    suspend fun insertMountainEntity(mountainEntity: MountainEntity)
}
