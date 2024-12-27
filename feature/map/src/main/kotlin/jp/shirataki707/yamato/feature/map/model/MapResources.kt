package jp.shirataki707.yamato.feature.map.model

import android.os.Parcelable
import jp.shirataki707.yamato.core.model.data.Mountain
import kotlinx.parcelize.Parcelize

@Parcelize
data class MapResources(
    val mountains: List<Mountain>,
) : Parcelable
