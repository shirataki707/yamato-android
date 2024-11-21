package jp.shirataki707.yamato.feature.home.contract

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import jp.shirataki707.yamato.core.ui.common.ModuleContract
import jp.shirataki707.yamato.core.ui.common.Navigator

interface HomeModuleContract : ModuleContract {

    @Composable
    fun HomePage(navigator: Navigator)
}

@Destination<RootGraph>(start = true)
@Composable
fun HomePage(contract: HomeModuleContract, navigator: Navigator) {
    contract.HomePage(navigator)
}
