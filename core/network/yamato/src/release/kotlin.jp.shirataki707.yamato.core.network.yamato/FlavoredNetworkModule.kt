ackage jp.shirataki707.yamato.core.network.yamato

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.network.yamato.retrofit.RetrofitYamatoNetwork

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: RetrofitYamatoNetwork): YamatoNetworkDataSource
}

