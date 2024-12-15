package jp.shirataki707.yamato.core.network.yamato.model

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
