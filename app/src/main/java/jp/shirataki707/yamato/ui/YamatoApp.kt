package jp.shirataki707.yamato.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.shirataki707.yamato.core.designsystem.component.YamatoBackground
import jp.shirataki707.yamato.core.designsystem.component.YamatoGradientBackground
import jp.shirataki707.yamato.core.designsystem.theme.LocalGradientColors

@Composable
fun YamatoAppHost(
    appState: YamatoAppState,
    modifier: Modifier = Modifier,
) {
    YamatoBackground(modifier = modifier) {
        YamatoGradientBackground(
            gradientColors = LocalGradientColors.current,
//            gradientColors = if (shouldShowGradientBackground) {
//                LocalGradientColors.current
//            } else {
//                GradientColors()
//            },
        ) {
            YamatoApp(
                appState = appState,
            )
        }

    }
}

@Composable
internal fun YamatoApp(
    appState: YamatoAppState,
) {

}