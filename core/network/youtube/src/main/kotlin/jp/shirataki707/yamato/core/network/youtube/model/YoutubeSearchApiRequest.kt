package jp.shirataki707.yamato.core.network.youtube.model

import kotlinx.serialization.SerialName

/**
 *  A class to model the request parameters for the Youtube API.
 *  https://developers.google.com/youtube/v3/docs/search/list?hl=ja
 */
data class YoutubeSearchApiRequest(
    // required
    /**
     * The part parameter specifies a comma-separated list of one or more search resource properties that the API response will include.
     * Acceptable values are:
     * - id: Returns only resource ID
     * - snippet: Returns snippet including resource's id, title, description, and thumbnail.
     */
    @SerialName("part")
    val part: String = "snippet",

    // optional
    /**
     *  The channelId parameter indicates that the API response should only contain resources created by the channel.
     */
    @SerialName("channelId")
    val channelId: String? = null,

    /**
     *  The maxResults parameter specifies the maximum number of items that should be returned in the result set.
     *  Acceptable values are 0 to 50, inclusive.
     *
     *  The default value is 5.
     */
    @SerialName("maxResults")
    val maxResults: Int? = null,

    /**
     *  The order parameter specifies the method that will be used to order resources in the API response.
     *  Acceptable values are:
     *  - date: Resources are sorted in reverse chronological order based on the date they were created.
     *  - rating: Resources are sorted from highest to lowest rating.
     *  - relevance: Resources are sorted based on their relevance to the search query.
     *  - title: Resources are sorted alphabetically by title.
     *  - videoCount: Channels are sorted in descending order of their number of uploaded videos.
     *  - viewCount: Resources are sorted from highest to lowest number of views.
     *
     *  The default value is relevance.
     */
    @SerialName("order")
    val order: String? = null,

    /**
     *  The q parameter specifies the query term to search for.
     *  Your request can also use the Boolean NOT (-) and OR (|) operators to exclude videos
     *  or to find videos that are associated with one of several search terms.
     */
    @SerialName("q")
    val keyword: String? = null,

    /**
     *  The type parameter restricts a search query to only retrieve a particular type of resource.
     *  Acceptable values are:
     *  - channel
     *  - playlist
     *  - video
     *
     *  The default value is "video, channel, playlist".
     */
    @SerialName("type")
    val type: String? = null,
) {
    enum class Order(
        val value: String,
    ) {
        DATE("date"),
        RATING("rating"),
        RELEVANCE("relevance"),
        TITLE("title"),
        VIDEO_COUNT("videoCount"),
        VIEW_COUNT("viewCount"),
    }

    enum class ResourceType(
        val value: String,
    ) {
        CHANNEL("channel"),
        PLAYLIST("playlist"),
        VIDEO("video"),
    }
}
