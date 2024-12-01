package jp.shirataki707.yamato.feature.home.main.ui

import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.model.data.DetailPageConfig
import jp.shirataki707.yamato.core.model.data.VideoResources
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlockType.Recommended
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertTrue

class HomePageViewModelTest {

    @Mock
    private lateinit var videoResourceRepository: VideoResourceRepository

    private lateinit var homePageViewModel: HomePageViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        homePageViewModel = HomePageViewModel(
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
        val actual = homePageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun isLoading_afterInitialLoadSuccess_false() = runTest {
        // Arrange
        whenever(videoResourceRepository.getVideoCarouselBlockTypeList()).thenReturn(
            listOf(VideoResources.VideoCarouselBlockType.Recommended),
        )
        whenever(videoResourceRepository.getVideoSummariesByKeyword(keyword = "keyword")).thenReturn(
            listOf(
                VideoResources.VideoCarouselBlock.VideoSummary(
                    videoTitle = "title",
                    channelName = "channel",
                    description = "description",
                    thumbnailUrl = "thumbnailUrl",
                    videoId = "videoId",
                    publishedAt = "publishedAt",
                ),
            ),
        )

        // Act
        homePageViewModel.initialLoadIfNeeded()

        // Assert
        val expected = false
        val actual = homePageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun isLoading_afterInitialLoadFailure_false() = runTest {
        // Arrange
        whenever(videoResourceRepository.getVideoCarouselBlockTypeList()).thenThrow(RuntimeException())

        // Act
        homePageViewModel.initialLoadIfNeeded()

        // Assert
        val expected = false
        val actual = homePageViewModel.isLoading
        assertEquals(expected, actual)
    }

    @Test
    fun videoResources_initialValue_null() {
        val expected = null
        val actual = homePageViewModel.videoResources
        assertEquals(expected, actual)
    }

    @Test
    fun videoResources_afterInitialLoadSuccess_parcelableResultSuccess() = runTest {
        // Arrange
        whenever(videoResourceRepository.getVideoCarouselBlockTypeList()).thenReturn(
            listOf(
                Recommended,
                VideoResources.VideoCarouselBlockType.Mountain("mountain"),
            ),
        )

        whenever(videoResourceRepository.getVideoSummariesByKeyword(keyword = "mountain")).thenReturn(
            listOf(
                VideoResources.VideoCarouselBlock.VideoSummary(
                    videoTitle = "title",
                    channelName = "channel",
                    description = "description",
                    thumbnailUrl = "thumbnailUrl",
                    videoId = "videoId",
                    publishedAt = "publishedAt",
                ),
            ),
        )

        // Act
        homePageViewModel.initialLoadIfNeeded()

        // Assert
        val expected = ParcelableResult.Success(
            VideoResources(
                videoCarouselBlocks = listOf(
                    VideoResources.VideoCarouselBlock(
                        blockTitle = "おすすめ",
                        blockType = Recommended,
                        videoSummaries = listOf(
                            VideoResources.VideoCarouselBlock.VideoSummary(
                                videoTitle = "title",
                                channelName = "channel",
                                description = "description",
                                thumbnailUrl = "thumbnailUrl",
                                videoId = "videoId",
                                publishedAt = "publishedAt",
                            ),
                        ),
                        detailPageConfig = DetailPageConfig(
                            detailPageTitle = "おすすめ",
                            carouselBlockType = Recommended,
                            keyword = "keyword",
                            channelId = null,
                            order = null,
                        ),
                    ),
                    VideoResources.VideoCarouselBlock(
                        blockTitle = "富士山",
                        blockType = VideoResources.VideoCarouselBlockType.Mountain("mountain"),
                        videoSummaries = emptyList(),
                        detailPageConfig = DetailPageConfig(
                            detailPageTitle = "mountain",
                            carouselBlockType = VideoResources.VideoCarouselBlockType.Mountain("mountain"),
                            keyword = "keyword",
                            channelId = null,
                            order = null,
                        ),
                    ),
                ),
            ),
        )

        val actual = homePageViewModel.videoResources

        assertTrue(actual is ParcelableResult.Failure)
        assertEquals(expected.result, actual.result)
    }

    @Test
    fun videoResources_afterInitialLoadFailure_parcelableResultFailure() = runTest {
        // Arrange
        whenever(videoResourceRepository.getVideoCarouselBlockTypeList()).thenThrow(RuntimeException())

        // Act
        homePageViewModel.initialLoadIfNeeded()

        // Assert
        val actual = homePageViewModel.videoResources
        assertTrue(actual is ParcelableResult.Failure)
    }
}
