package jp.shirataki707.yamato.core.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class VideoResources(
    val videoCarouselBlocks: List<VideoCarouselBlock>,
) : Parcelable {

    @Parcelize
    data class VideoCarouselBlock(
        val blockTitle: String,
        val blockType: VideoCarouselBlockType,
        val videoSummaries: List<VideoSummary>,
        val detailPageConfig: DetailPageConfig,
    ) : Parcelable {

        @Parcelize
        data class VideoSummary(
            val videoTitle: String,
            val channelName: String,
            val description: String,
            val thumbnailUrl: String,
            val videoId: String,
            val publishedAt: String,
        ) : Parcelable
    }

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
