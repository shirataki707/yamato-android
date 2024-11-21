package jp.shirataki707.yamato.feature.home.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.spec.NavGraphSpec
import jp.shirataki707.yamato.core.ui.common.ModuleContract.Companion.registerModuleContract
import jp.shirataki707.yamato.core.ui.common.Navigator
import jp.shirataki707.yamato.feature.home.contract.HomeModuleContract

object HomeModule : HomeModuleContract {
    override val navGraph: NavGraphSpec
        get() = HomeNavGraph

    override val internalNavGraph: NavGraphSpec?
        get() = null

    override fun <T> addDependency(
        dependenciesContainerBuilder: DependenciesContainerBuilder<T>,
        argument: Bundle,
    ) {
        dependenciesContainerBuilder.registerModuleContract<T, HomeModuleContract, HomeModule>(this)
    }

    @Composable
    override fun HomePage(navigator: Navigator) {
        HomePageHost(navigator = navigator)
    }
}

//@Destination<RootGraph>
//@Composable ExamplePage() {
//    ExamplePageHost()
//}
