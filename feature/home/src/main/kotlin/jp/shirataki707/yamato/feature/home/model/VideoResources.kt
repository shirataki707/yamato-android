package jp.shirataki707.yamato.feature.home.model

import android.os.Parcelable
import jp.shirataki707.yamato.core.model.data.Video
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class VideoResources(
    val videos: List<Video>,
) : Parcelable
