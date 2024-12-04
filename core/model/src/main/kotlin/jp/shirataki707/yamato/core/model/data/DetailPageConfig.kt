package jp.shirataki707.yamato.core.model.data

import android.os.Parcelable
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlockType
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest.Order
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class DetailPageConfig(
    val detailPageTitle: String,
    val carouselBlockType: VideoCarouselBlockType,
    val keyword: String,
    val channelId: String? = null,
    val order: Order? = null,
) : Parcelable
