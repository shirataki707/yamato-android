package jp.shirataki707.yamato.core.data.repository

interface YoutubeVideoRepository {
    suspend fun searchVideosByKeyword(keyword: String): List<String>
//    suspend fun searchVideosByChannel(channelId: String): List<String>
}
