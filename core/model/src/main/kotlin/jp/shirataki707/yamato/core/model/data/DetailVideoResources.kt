package jp.shirataki707.yamato.core.model.data

import android.os.Parcelable
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlock.VideoSummary
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailVideoResources(
    val detailPageTitle: String,
    val videoSummaries: List<VideoSummary>,
) : Parcelable
