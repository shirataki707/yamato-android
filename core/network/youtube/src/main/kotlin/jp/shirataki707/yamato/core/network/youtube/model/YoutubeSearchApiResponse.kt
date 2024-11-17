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
        @SerialName("snippet")
        val snippet: Snippet,
    )
}
