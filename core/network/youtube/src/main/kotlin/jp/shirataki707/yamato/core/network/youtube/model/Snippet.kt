package jp.shirataki707.yamato.core.network.youtube.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snippet(
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("channelId")
    val channelId: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("thumbnails")
    val thumbnails: Thumbnails,
    @SerialName("channelTitle")
    val channelTitle: String,
) {
    @Serializable
    data class Thumbnails(
        @SerialName("default")
        val default: Thumbnail,
        @SerialName("medium")
        val medium: Thumbnail,
        @SerialName("high")
        val high: Thumbnail,
    ) {
        @Serializable
        data class Thumbnail(
            @SerialName("url")
            val url: String,
            @SerialName("width")
            val width: Int,
            @SerialName("height")
            val height: Int,
        )
    }
}
