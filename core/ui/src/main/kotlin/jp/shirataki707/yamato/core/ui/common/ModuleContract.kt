package jp.shirataki707.yamato.core.ui.common

import android.os.Bundle
import com.ramcosta.composedestinations.manualcomposablecalls.ManualComposableCallsBuilder
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.NavGraphSpec

interface ModuleContract {

    companion object {

        inline fun <TContainer, reified TModuleContract, TModule> DependenciesContainerBuilder<TContainer>.registerModuleContract(
            module: TModule
        ) where TModuleContract : ModuleContract, TModule : TModuleContract {
            dependency<TModuleContract, TContainer>(module)
        }
    }


    val navGraph: NavGraphSpec

    val internalNavGraph: NavGraphSpec?

    fun <T> addDependency(dependenciesContainerBuilder: DependenciesContainerBuilder<T>, argument: Bundle)

    fun buildManualComposableCalls(manualComposableCallsBuilder: ManualComposableCallsBuilder) {}
}
