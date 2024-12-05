package jp.shirataki707.yamato.core.model.data

import android.os.Parcelable
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest.Order
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Video(
    val videoBlockInfo: VideoBlockInfo,
    val videoSummaries: List<VideoSummary>,
) : Parcelable {

    @Serializable
    @Parcelize
    data class VideoBlockInfo(
        val videoBlockTitle: String,
        val videoCarouselBlockType: VideoCarouselBlockType,
        val searchKeyword: String,
        val searchChannelId: String? = null,
        val searchOrder: Order? = null,
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

    @Serializable
    sealed class VideoCarouselBlockType : Parcelable {

        @Serializable
        @Parcelize
        data object Recommended : VideoCarouselBlockType()

        @Serializable
        @Parcelize
        data object Popular : VideoCarouselBlockType()

        @Serializable
        @Parcelize
        data object Latest : VideoCarouselBlockType()

        @Serializable
        @Parcelize
        data class Mountain(val mountainName: String) : VideoCarouselBlockType()

        @Serializable
        @Parcelize
        data class Channel(val channelId: String) : VideoCarouselBlockType()
    }
}
