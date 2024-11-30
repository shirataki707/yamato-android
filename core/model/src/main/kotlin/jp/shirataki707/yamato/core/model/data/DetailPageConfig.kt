package jp.shirataki707.yamato.core.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class DetailPageConfig(
    val keyword: String? = null,
    val channelId: String? = null,
) : Parcelable
