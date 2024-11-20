package jp.shirataki707.yamato.core.network.youtube.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *  A class to model the response parameters for the Youtube API.
 *  https://developers.google.com/youtube/v3/docs/search/list?hl=ja
 */
@Serializable
data class YoutubeSearchApiResponse(
    @SerialName("nextPageToken")
    val nextPageToken: String? = null,
    @SerialName("prevPageToken")
    val prevPageToken: String? = null,
    @SerialName("pageInfo")
    val pageInfo: PageInfo,
    @SerialName("items")
    val items: List<SearchResultItem>,
) {
    @Serializable
    data class PageInfo(
        @SerialName("totalResults")
        val totalResults: Int,
        @SerialName("resultsPerPage")
        val resultsPerPage: Int,
    )

    @Serializable
    data class SearchResultItem(
        @SerialName("id")
        val resourceId: ResourceId,
        @SerialName("snippet")
        val snippet: Snippet,
    ) {
        @Serializable
        data class ResourceId(
            @SerialName("kind")
            val kind: String,
            @SerialName("videoId")
            val videoId: String,
        )
    }
}

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
