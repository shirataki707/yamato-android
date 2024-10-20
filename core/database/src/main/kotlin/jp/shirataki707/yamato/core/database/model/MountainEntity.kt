package jp.shirataki707.yamato.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mountains")
data class MountainEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageName: String,
    val shortDescription: String,
    val longDescription: String,
    val elevation: Int,
    val latitude: Double,
    val longitude: Double,
    @ColumnInfo(defaultValue = "false")
    val isClimbed: Boolean,
)
