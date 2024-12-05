package jp.shirataki707.yamato.feature.video.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import jp.shirataki707.yamato.core.common.navigation.CustomNavType
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.feature.video.ui.VideoPageHost
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class VideoRoute(val videoBlockInfo: VideoBlockInfo)

fun NavController.navigateToVideo(
    videoBlockInfo: VideoBlockInfo,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(route = VideoRoute(videoBlockInfo)) {
        navOptions()
    }
}

fun NavGraphBuilder.videoPage(
    onBackClick: () -> Unit,
) {
    composable<VideoRoute>(
        typeMap = mapOf(
            typeOf<VideoBlockInfo>() to CustomNavType.nonNullableNavType<VideoBlockInfo>(),
        ),
    ) { backStackEntry ->
        val videoPageConfig = backStackEntry.toRoute<VideoRoute>().videoBlockInfo
        VideoPageHost(
            videoBlockInfo = videoPageConfig,
            onBackClick = onBackClick,
        )
    }
}
