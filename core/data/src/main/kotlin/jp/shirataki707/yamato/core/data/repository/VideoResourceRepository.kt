package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.data.VideoResourceManager.getVideoCarouselBlockTypeList
import jp.shirataki707.yamato.core.model.data.Video
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Channel
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Latest
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Mountain
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Popular
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Recommended
import jp.shirataki707.yamato.core.model.data.Video.VideoSummary
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

interface VideoResourceRepository {
    suspend fun getVideoSummariesByKeyword(
        keyword: String,
        channelId: String? = null,
        maxResults: Int? = null,
        order: YoutubeSearchApiRequest.Order? = YoutubeSearchApiRequest.Order.RELEVANCE,
        resourceType: YoutubeSearchApiRequest.ResourceType? = YoutubeSearchApiRequest.ResourceType.VIDEO,
    ): List<VideoSummary>

    suspend fun getCarouselBlockVideoResources(): List<Video>
}

internal class VideoResourceRepositoryImpl @Inject constructor(
    private val youtubeDataSource: YoutubeDataSource,
) : VideoResourceRepository {

    override suspend fun getVideoSummariesByKeyword(
        keyword: String,
        channelId: String?,
        maxResults: Int?,
        order: YoutubeSearchApiRequest.Order?,
        resourceType: YoutubeSearchApiRequest.ResourceType?,
    ): List<VideoSummary> {
        val request = YoutubeSearchApiRequest(
            keyword = keyword,
            channelId = channelId,
            maxResults = maxResults,
            order = order?.value,
            type = resourceType?.value,
        )
        val response = youtubeDataSource.getVideoResources(request)
        return response.items.map { item ->
            VideoSummary(
                videoTitle = item.snippet.title,
                channelName = item.snippet.channelTitle,
                description = item.snippet.description,
                thumbnailUrl = item.snippet.thumbnails.high.url,
                videoId = item.resourceId.videoId,
                publishedAt = item.snippet.publishedAt,
            )
        }
    }

    override suspend fun getCarouselBlockVideoResources(): List<Video> =
        coroutineScope {
            val videoCarouselBlockTypes = getVideoCarouselBlockTypeList()
            val deferredCarouselBlocks = videoCarouselBlockTypes.map { blockType ->
                async { getVideoSummaries(blockType) }
            }.awaitAll()

            videoCarouselBlockTypes.mapIndexed { index, blockType ->
                val blockTitle = when (blockType) {
                    Recommended -> {
                        "おすすめ"
                    }

                    Latest -> {
                        "最新"
                    }

                    Popular -> {
                        "人気"
                    }

                    is Mountain -> {
                        blockType.mountainName
                    }

                    is Channel -> {
                        deferredCarouselBlocks[index].first().channelName
                    }
                }
                Video(
                    videoSummaries = deferredCarouselBlocks[index],
                    videoBlockInfo = VideoBlockInfo(
                        videoBlockTitle = blockTitle,
                        videoCarouselBlockType = blockType,
                        searchKeyword = getSearchKeyword(blockType),
                        searchChannelId = if (blockType is Channel) blockType.channelId else null,
                        searchOrder = getSearchOrder(blockType),
                    ),
                )
            }
        }

    private suspend fun getVideoSummaries(blockType: VideoCarouselBlockType) =
        when (blockType) {
            Recommended -> {
                // TODO: Implement recommendation logic
                getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                )
            }

            Popular -> {
                // TODO: Implement popularity logic
                getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                    order = getSearchOrder(blockType),
                )
            }

            Latest -> {
                getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                    order = getSearchOrder(blockType),
                )
            }

            is Mountain -> {
                getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                )
            }

            is Channel -> {
                getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                    channelId = blockType.channelId,
                )
            }
        }

    private fun getSearchKeyword(blockType: VideoCarouselBlockType): String =
        when (blockType) {
            is Channel, Recommended -> "登山"
            Popular, Latest -> "日本百名山 登山"
            is Mountain -> "${blockType.mountainName} 登山"
        }

    private fun getSearchOrder(blockType: VideoCarouselBlockType): YoutubeSearchApiRequest.Order? =
        when (blockType) {
            Popular -> YoutubeSearchApiRequest.Order.VIEW_COUNT
            Latest -> YoutubeSearchApiRequest.Order.DATE
            else -> null
        }
}
