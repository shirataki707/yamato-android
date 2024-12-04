package jp.shirataki707.yamato.feature.home.detail.ui

import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.model.data.video.DetailPageConfig
import jp.shirataki707.yamato.core.model.data.video.DetailVideoResources
import jp.shirataki707.yamato.core.model.data.video.VideoResources
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlock.VideoSummary
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlockType.Recommended
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
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

class DetailPageViewModelTest {

    @Mock
    private lateinit var videoResourceRepository: VideoResourceRepository

    private lateinit var detailPageViewModel: DetailPageViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        detailPageViewModel = DetailPageViewModel(
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
        val actual = detailPageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun isLoading_afterInitialLoadSuccess_false() = runTest {
        // Arrange
        whenever(videoResourceRepository.getCarouselBlockVideoResources()).thenReturn(
            VideoResources(
                videoCarouselBlocks = emptyList(),
            ),
        )

        // Act
        detailPageViewModel.initialLoadIfNeeded(detailPageConfig)

        // Assert
        val expected = false
        val actual = detailPageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun isLoading_afterInitialLoadFailure_false() = runTest {
        // Arrange
        whenever(videoResourceRepository.getCarouselBlockVideoResources()).thenThrow(
            RuntimeException(),
        )

        // Act
        detailPageViewModel.initialLoadIfNeeded(detailPageConfig)

        // Assert
        val expected = false
        val actual = detailPageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun detailVideoResources_initialValue_null() {
        val expected = null
        val actual = detailPageViewModel.detailVideoResources
        assertEquals(expected, actual)
    }

    @Test
    fun detailVideoResources_afterInitialLoadSuccess_parcelableResultSuccess() = runTest {
        // Arrange
        whenever(
            videoResourceRepository.getVideoSummariesByKeyword(
                keyword = "keyword",
                maxResults = 30,
            ),
        ).thenReturn(
            listOf(
                VideoResources.VideoCarouselBlock.VideoSummary(
                    videoTitle = "title1",
                    channelName = "channel1",
                    description = "description1",
                    thumbnailUrl = "thumbnailUrl1",
                    videoId = "videoId1",
                    publishedAt = "publishedAt1",
                ),
                VideoResources.VideoCarouselBlock.VideoSummary(
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
        detailPageViewModel.initialLoadIfNeeded(detailPageConfig)

        // Assert
        val expected = ParcelableResult.Success(
            DetailVideoResources(
                detailPageTitle = "おすすめ",
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

        val actual = detailPageViewModel.detailVideoResources

        assertTrue(actual is ParcelableResult.Success)
        assertEquals(expected.result, actual!!.result)
    }

    @Test
    fun detailVideoResources_afterInitialLoadFailure_parcelableResultFailure() = runTest {
        // Arrange
        whenever(videoResourceRepository.getVideoSummariesByKeyword("keyword")).thenThrow(
            RuntimeException(),
        )

        // Act
        detailPageViewModel.initialLoadIfNeeded(detailPageConfig)

        // Assert
        val actual = detailPageViewModel.detailVideoResources

        assertTrue(actual is ParcelableResult.Failure)
    }

    private val detailPageConfig = DetailPageConfig(
        detailPageTitle = "おすすめ",
        carouselBlockType = Recommended,
        keyword = "keyword",
    )
}
