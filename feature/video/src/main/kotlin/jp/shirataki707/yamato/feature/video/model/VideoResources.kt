package jp.shirataki707.yamato.feature.video.model

import android.os.Parcelable
import jp.shirataki707.yamato.core.model.data.Video.VideoSummary
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class VideoResources(
    val videoPageTitle: String,
    val videoSummaries: List<VideoSummary>,
) : Parcelable
