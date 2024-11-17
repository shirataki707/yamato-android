package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.model.data.YoutubeVideoResource

interface YoutubeVideoRepository {
    suspend fun searchVideosByKeyword(keyword: String): List<YoutubeVideoResource>
//    suspend fun searchVideosByChannel(channelId: String): List<String>
}
