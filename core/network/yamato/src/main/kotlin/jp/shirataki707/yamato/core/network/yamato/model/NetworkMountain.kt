package jp.shirataki707.yamato.core.network.yamato.model

import jp.shirataki707.yamato.core.model.data.Mountain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [Mountain] when fetched from /mountains
 */
@Serializable
data class NetworkMountain(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("reading_name")
    val readingName: String,
    @SerialName("image_path")
    val imagePath: String,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("long_description")
    val longDescription: String,
    @SerialName("elevation")
    val elevation: Int,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("region")
    val region: String,
    @SerialName("prefectures")
    val prefectures: String,
    @SerialName("is_climbed")
    val isClimbed: Boolean,
    @SerialName("schedule")
    val schedule: String,
    @SerialName("physical_strength")
    val physicalStrength: String,
    @SerialName("difficulty")
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
