package jp.shirataki707.yamato.feature.home.main.ui

import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.model.data.video.DetailPageConfig
import jp.shirataki707.yamato.core.model.data.video.VideoResources
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlock
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlock.VideoSummary
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlockType.Mountain
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
        whenever(videoResourceRepository.getCarouselBlockVideoResources()).thenReturn(
            VideoResources(
                videoCarouselBlocks = emptyList(),
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
        whenever(videoResourceRepository.getCarouselBlockVideoResources()).thenThrow(
            RuntimeException(),
        )

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
        whenever(videoResourceRepository.getCarouselBlockVideoResources()).thenReturn(
            VideoResources(
                videoCarouselBlocks = listOf(
                    VideoCarouselBlock(
                        blockTitle = "おすすめ",
                        blockType = Recommended,
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
                        detailPageConfig = DetailPageConfig(
                            detailPageTitle = "おすすめ",
                            carouselBlockType = Recommended,
                            keyword = "keyword",
                            channelId = null,
                            order = null,
                        ),
                    ),
                    VideoCarouselBlock(
                        blockTitle = "富士山",
                        blockType = Mountain("mountain"),
                        videoSummaries = emptyList(),
                        detailPageConfig = DetailPageConfig(
                            detailPageTitle = "mountain",
                            carouselBlockType = Mountain("mountain"),
                            keyword = "keyword",
                            channelId = null,
                            order = null,
                        ),
                    ),
                ),
            ),
        )

        // Act
        homePageViewModel.initialLoadIfNeeded()

        // Assert
        val expected = ParcelableResult.Success(
            VideoResources(
                videoCarouselBlocks = listOf(
                    VideoCarouselBlock(
                        blockTitle = "おすすめ",
                        blockType = Recommended,
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
                        detailPageConfig = DetailPageConfig(
                            detailPageTitle = "おすすめ",
                            carouselBlockType = Recommended,
                            keyword = "keyword",
                            channelId = null,
                            order = null,
                        ),
                    ),
                    VideoCarouselBlock(
                        blockTitle = "富士山",
                        blockType = Mountain("mountain"),
                        videoSummaries = emptyList(),
                        detailPageConfig = DetailPageConfig(
                            detailPageTitle = "mountain",
                            carouselBlockType = Mountain("mountain"),
                            keyword = "keyword",
                            channelId = null,
                            order = null,
                        ),
                    ),
                ),
            ),
        )

        val actual = homePageViewModel.videoResources

        assertTrue(actual is ParcelableResult.Success)
        assertEquals(expected.result, actual!!.result)
    }

    @Test
    fun videoResources_afterInitialLoadFailure_parcelableResultFailure() = runTest {
        // Arrange
        whenever(videoResourceRepository.getCarouselBlockVideoResources()).thenThrow(
            RuntimeException(),
        )

        // Act
        homePageViewModel.initialLoadIfNeeded()

        // Assert
        val actual = homePageViewModel.videoResources
        assertTrue(actual is ParcelableResult.Failure)
    }
}
