package jp.shirataki707.yamato.core.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mountain(
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
    val physicalStrength: Int,
    val difficulty: Int,
) : Parcelable
