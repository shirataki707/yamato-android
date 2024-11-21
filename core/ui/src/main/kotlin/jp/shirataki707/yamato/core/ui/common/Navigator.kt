package jp.shirataki707.yamato.core.ui.common

import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.Route

interface Navigator {

    fun navigate(
        direction: Direction,
        onlyIfResumed: Boolean = false,
        builder: NavOptionsBuilder.() -> Unit = {},
    )

    fun navigateBack()

    fun navigateBackTo(route: Route, inclusive: Boolean)
}
