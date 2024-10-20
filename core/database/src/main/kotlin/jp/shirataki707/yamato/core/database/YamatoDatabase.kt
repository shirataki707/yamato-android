package jp.shirataki707.yamato.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.shirataki707.yamato.core.database.dao.MountainDao
import jp.shirataki707.yamato.core.database.model.MountainEntity

@Database(
    entities = [MountainEntity::class],
    version = 1,
    exportSchema = true,
)
internal abstract class YamatoDatabase : RoomDatabase() {
    abstract fun mountainDao(): MountainDao
}
