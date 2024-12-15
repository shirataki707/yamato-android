package jp.shirataki707.yamato.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.shirataki707.yamato.core.model.data.Mountain

@Entity(tableName = "mountains")
data class MountainEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "image_filename")
    val imageName: String,
    @ColumnInfo(name = "short_description")
    val shortDescription: String,
    @ColumnInfo(name = "long_description")
    val longDescription: String,
    val elevation: Int,
    val latitude: Double,
    val longitude: Double,
    @ColumnInfo(name = "is_climbed")
    val isClimbed: Boolean,
)

//fun MountainEntity.asExternalModel() = Mountain(
//    id = id,
//    name = name,
//    imageName = imageName,
//    shortDescription = shortDescription,
//    longDescription = longDescription,
//    elevation = elevation,
//    latitude = latitude,
//    longitude = longitude,
//    isClimbed = isClimbed,
//)
