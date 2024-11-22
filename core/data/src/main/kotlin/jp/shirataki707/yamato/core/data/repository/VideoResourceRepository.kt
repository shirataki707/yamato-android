package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.model.data.VideoSummary
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest
import javax.inject.Inject

interface VideoResourceRepository {
    suspend fun getVideoResourcesByKeyword(keyword: String): List<VideoSummary>
//    suspend fun searchVideosByChannel(channelId: String): List<String>
}

internal class VideoResourceRepositoryImpl @Inject constructor(
    private val youtubeDataSource: YoutubeDataSource,
) : VideoResourceRepository {

    override suspend fun getVideoResourcesByKeyword(keyword: String): List<VideoSummary> {
        val request = YoutubeSearchApiRequest(
            keyword = keyword,
            type = "video",
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
}
