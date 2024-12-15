package jp.shirataki707.yamato.core.network.yamato

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.network.yamato.retrofit.RetrofitYamatoNetwork

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    // TODO: バックエンドが完成したらDemo用のDataSourceに切り替える
    @Binds
    fun binds(impl: RetrofitYamatoNetwork): YamatoNetworkDataSource
}

