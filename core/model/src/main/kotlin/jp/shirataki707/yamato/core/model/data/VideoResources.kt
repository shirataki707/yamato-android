package jp.shirataki707.yamato.core.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoResources(
    val videoCarouselBlocks: List<VideoCarouselBlock>,
) : Parcelable {

    @Parcelize
    data class VideoCarouselBlock(
        val blockTitle: String,
        val blockType: VideoCarouselBlockType,
        val videoSummaries: List<VideoSummary>,
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

    sealed class VideoCarouselBlockType : Parcelable {
        @Parcelize
        data object Recommended : VideoCarouselBlockType()

        @Parcelize
        data object Popular : VideoCarouselBlockType()

        @Parcelize
        data object Latest : VideoCarouselBlockType()

        @Parcelize
        data class Mountain(val mountainName: String) : VideoCarouselBlockType()

        @Parcelize
        data class Channel(val channelId: String) : VideoCarouselBlockType()
    }
}
