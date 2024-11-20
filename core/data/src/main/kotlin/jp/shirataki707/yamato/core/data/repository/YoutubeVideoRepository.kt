package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.model.data.VideoSummary

interface YoutubeVideoRepository {
    suspend fun getVideoResourcesByKeyword(keyword: String): List<VideoSummary>
//    suspend fun searchVideosByChannel(channelId: String): List<String>
}
