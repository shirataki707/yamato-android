package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.model.data.YoutubeVideoResource
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest
import javax.inject.Inject

internal class YoutubeVideoRepositoryImpl @Inject constructor(
    private val youtubeDataSource: YoutubeDataSource,
) : YoutubeVideoRepository {

    override suspend fun searchVideosByKeyword(keyword: String): List<YoutubeVideoResource> {
        val request = YoutubeSearchApiRequest(
            keyword = keyword,
            type = "video",
        )
        val response = youtubeDataSource.search(request)
        return response.items.map { item ->
            YoutubeVideoResource(
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
