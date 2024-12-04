package jp.shirataki707.yamato.feature.video

import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.model.data.Video
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Mountain
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Recommended
import jp.shirataki707.yamato.core.model.data.Video.VideoSummary
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.video.model.VideoResources
import jp.shirataki707.yamato.feature.video.ui.VideoPageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

internal class VideoPageViewModelTest {

    @Mock
    private lateinit var videoResourceRepository: VideoResourceRepository

    private lateinit var videoPageViewModel: VideoPageViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        videoPageViewModel = VideoPageViewModel(
            ioDispatcher = testDispatcher,
            videoResourceRepository = videoResourceRepository,
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun isLoading_initialValue_false() {
        val expected = false
        val actual = videoPageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun isLoading_afterInitialLoadSuccess_false() = runTest {
        // Arrange
        whenever(videoResourceRepository.getCarouselBlockVideoResources()).thenReturn(
            listOf(
                Video(
                    videoSummaries = listOf(
                        VideoSummary(
                            videoTitle = "title",
                            channelName = "channel",
                            description = "description",
                            thumbnailUrl = "thumbnailUrl",
                            videoId = "videoId",
                            publishedAt = "publishedAt",
                        ),
                    ),
                    videoBlockInfo = VideoBlockInfo(
                        videoBlockTitle = "おすすめ",
                        videoCarouselBlockType = Recommended,
                        searchKeyword = "searchKeyword",
                        searchChannelId = null,
                        searchOrder = null,
                    ),
                ),
                Video(
                    videoSummaries = emptyList(),
                    videoBlockInfo = VideoBlockInfo(
                        videoBlockTitle = "mountain",
                        videoCarouselBlockType = Mountain("mountain"),
                        searchKeyword = "searchKeyword",
                        searchChannelId = null,
                        searchOrder = null,
                    ),
                ),
            ),
        )

        // Act
        videoPageViewModel.initialLoadIfNeeded(videoBlockInfo)

        // Assert
        val expected = false
        val actual = videoPageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun isLoading_afterInitialLoadFailure_false() = runTest {
        // Arrange
        whenever(videoResourceRepository.getCarouselBlockVideoResources()).thenThrow(
            RuntimeException(),
        )

        // Act
        videoPageViewModel.initialLoadIfNeeded(videoBlockInfo)

        // Assert
        val expected = false
        val actual = videoPageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun detailVideoResources_initialValue_null() {
        val expected = null
        val actual = videoPageViewModel.videoResources
        assertEquals(expected, actual)
    }

    @Test
    fun detailVideoResources_afterInitialLoadSuccess_parcelableResultSuccess() = runTest {
        // Arrange
        whenever(
            videoResourceRepository.getVideoSummariesByKeyword(
                keyword = "searchKeyword",
                maxResults = 30,
            ),
        ).thenReturn(
            listOf(
                VideoSummary(
                    videoTitle = "title1",
                    channelName = "channel1",
                    description = "description1",
                    thumbnailUrl = "thumbnailUrl1",
                    videoId = "videoId1",
                    publishedAt = "publishedAt1",
                ),
                VideoSummary(
                    videoTitle = "title2",
                    channelName = "channel2",
                    description = "description2",
                    thumbnailUrl = "thumbnailUrl2",
                    videoId = "videoId2",
                    publishedAt = "publishedAt2",
                ),
            ),
        )

        // Act
        videoPageViewModel.initialLoadIfNeeded(videoBlockInfo)

        // Assert
        val expected = ParcelableResult.Success(
            VideoResources(
                videoPageTitle = "おすすめ",
                videoSummaries = listOf(
                    VideoSummary(
                        videoTitle = "title1",
                        channelName = "channel1",
                        description = "description1",
                        thumbnailUrl = "thumbnailUrl1",
                        videoId = "videoId1",
                        publishedAt = "publishedAt1",
                    ),
                    VideoSummary(
                        videoTitle = "title2",
                        channelName = "channel2",
                        description = "description2",
                        thumbnailUrl = "thumbnailUrl2",
                        videoId = "videoId2",
                        publishedAt = "publishedAt2",
                    ),
                ),
            ),
        )

        val actual = videoPageViewModel.videoResources

        assertTrue(actual is ParcelableResult.Success)
        assertEquals(expected.result, actual!!.result)
    }

    @Test
    fun detailVideoResources_afterInitialLoadFailure_parcelableResultFailure() = runTest {
        // Arrange
        whenever(videoResourceRepository.getVideoSummariesByKeyword("searchKeyword")).thenThrow(
            RuntimeException(),
        )

        // Act
        videoPageViewModel.initialLoadIfNeeded(videoBlockInfo)

        // Assert
        val actual = videoPageViewModel.videoResources

        assertTrue(actual is ParcelableResult.Failure)
    }

    private val videoBlockInfo = VideoBlockInfo(
        videoBlockTitle = "おすすめ",
        videoCarouselBlockType = Recommended,
        searchKeyword = "searchKeyword",
    )
}
