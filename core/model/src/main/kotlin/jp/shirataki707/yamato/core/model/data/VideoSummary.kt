package jp.shirataki707.yamato.core.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoResources(
    val videoSummaries: List<VideoSummary>,
) : Parcelable


@Parcelize
data class VideoSummary(
    val videoTitle: String,
    val channelName: String,
    val description: String,
    val thumbnailUrl: String,
    val videoId: String,
    val publishedAt: String,
) : Parcelable
