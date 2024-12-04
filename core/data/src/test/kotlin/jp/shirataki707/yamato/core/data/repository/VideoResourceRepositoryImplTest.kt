package jp.shirataki707.yamato.core.data.repository

import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlock.VideoSummary
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.model.Snippet
import jp.shirataki707.yamato.core.network.youtube.model.Snippet.Thumbnails
import jp.shirataki707.yamato.core.network.youtube.model.Snippet.Thumbnails.Thumbnail
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiResponse
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiResponse.SearchResultItem
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class VideoResourceRepositoryImplTest {

    private lateinit var youtubeDataSource: YoutubeDataSource
    private lateinit var videoResourceRepository: VideoResourceRepositoryImpl

    @BeforeEach
    fun setUp() {
        youtubeDataSource = mock(YoutubeDataSource::class.java)
        videoResourceRepository = VideoResourceRepositoryImpl(youtubeDataSource)
    }

    @Test
    fun getVideoSummariesByKeyword_shouldReturnVideoSummaries() = runTest {
        // Arrange
        whenever(youtubeDataSource.getVideoResources(any())).thenReturn(
            YoutubeSearchApiResponse(
                pageInfo = YoutubeSearchApiResponse.PageInfo(
                    totalResults = 1,
                    resultsPerPage = 1,
                ),
                items = listOf(
                    SearchResultItem(
                        resourceId = SearchResultItem.ResourceId(
                            kind = "kind",
                            videoId = "12345",
                        ),
                        snippet = Snippet(
                            publishedAt = "2021-01-01T00:00:00Z",
                            channelId = "searchChannelId",
                            title = "登山やってみた",
                            description = "富士山に登りました",
                            thumbnails = Thumbnails(
                                default = Thumbnail(
                                    url = "thumbnailUrl",
                                    width = 320,
                                    height = 180,
                                ),
                                medium = Thumbnail(
                                    url = "thumbnailUrl",
                                    width = 480,
                                    height = 360,
                                ),
                                high = Thumbnail(
                                    url = "thumbnailUrl",
                                    width = 1280,
                                    height = 720,
                                ),
                            ),
                            channelTitle = "しらたきチャンネル",
                        ),
                    ),
                ),
            ),
        )

        // Act
        val response = videoResourceRepository.getVideoSummariesByKeyword("searchKeyword")

        // Assert
        val expected = VideoSummary(
            videoTitle = "登山やってみた",
            channelName = "しらたきチャンネル",
            description = "富士山に登りました",
            thumbnailUrl = "thumbnailUrl",
            videoId = "12345",
            publishedAt = "2021-01-01T00:00:00Z",
        )

        val actual = response.first()

        assertEquals(expected, actual)
    }

    // TODO: Add test after videoResourceManager is implemented
    @Test
    fun getCarouselBlockVideoResources_returnVideoResources() = runTest {
    }
}
