package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.data.VideoResourceManager
import jp.shirataki707.yamato.core.model.data.VideoResources
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest
import javax.inject.Inject

interface VideoResourceRepository {
    suspend fun getVideoCarouselBlockTypeList(): List<VideoResources.VideoCarouselBlockType>
    suspend fun getVideoSummariesByKeyword(
        keyword: String,
        channelId: String? = null,
        maxResults: Int? = null,
        order: YoutubeSearchApiRequest.Order? = YoutubeSearchApiRequest.Order.RELEVANCE,
        resourceType: YoutubeSearchApiRequest.ResourceType? = YoutubeSearchApiRequest.ResourceType.VIDEO,
    ): List<VideoResources.VideoCarouselBlock.VideoSummary>
//    suspend fun searchVideosByChannel(channelId: String): List<String>
}

internal class VideoResourceRepositoryImpl @Inject constructor(
    private val youtubeDataSource: YoutubeDataSource,
) : VideoResourceRepository {

    override suspend fun getVideoCarouselBlockTypeList(): List<VideoResources.VideoCarouselBlockType> {
        return VideoResourceManager.getVideoCarouselBlockTypeList()
    }

    override suspend fun getVideoSummariesByKeyword(
        keyword: String,
        channelId: String?,
        maxResults: Int?,
        order: YoutubeSearchApiRequest.Order?,
        resourceType: YoutubeSearchApiRequest.ResourceType?,
    ): List<VideoResources.VideoCarouselBlock.VideoSummary> {
        val request = YoutubeSearchApiRequest(
            keyword = keyword,
            channelId = channelId,
            maxResults = maxResults,
            order = order?.value,
            type = resourceType?.value,
        )
        val response = youtubeDataSource.getVideoResources(request)
        return response.items.map { item ->
            VideoResources.VideoCarouselBlock.VideoSummary(
                videoTitle = item.snippet.title,
                channelName = item.snippet.channelTitle,
                description = item.snippet.description,
                thumbnailUrl = item.snippet.thumbnails.high.url,
                videoId = item.resourceId.videoId,
                publishedAt = item.snippet.publishedAt,
            )
        }
    }
}
