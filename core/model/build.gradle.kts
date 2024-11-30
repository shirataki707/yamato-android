plugins {
    alias(libs.plugins.yamato.android.library)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "jp.shirataki707.yamato.core.model"
}

dependencies {
    api(projects.core.network.youtube)
    implementation(libs.kotlinx.serialization.json)
}
