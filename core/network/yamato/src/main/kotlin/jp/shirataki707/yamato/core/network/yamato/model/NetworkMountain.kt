package jp.shirataki707.yamato.core.network.yamato.model

import jp.shirataki707.yamato.core.model.data.Mountain
import kotlinx.serialization.Serializable

/**
 * Network representation of [Mountain] when fetched from /mountains
 */
@Serializable
data class NetworkMountain(
    val id: Int,
    val name: String,
    val readingName: String,
    val imagePath: String,
    val shortDescription: String,
    val longDescription: String,
    val elevation: Int,
    val latitude: Double,
    val longitude: Double,
    val region: String,
    val prefectures: String,
    val isClimbed: Boolean,
    val schedule: String,
    val physicalStrength: String,
    val difficulty: String,
)

fun NetworkMountain.asExternalModel(): Mountain = Mountain(
    id = id,
    name = name,
    readingName = readingName,
    imagePath = imagePath,
    shortDescription = shortDescription,
    longDescription = longDescription,
    elevation = elevation,
    latitude = latitude,
    longitude = longitude,
    region = region,
    prefectures = prefectures,
    isClimbed = isClimbed,
    schedule = schedule,
    physicalStrength = physicalStrength,
    difficulty = difficulty,
)
