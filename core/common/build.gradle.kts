plugins {
    alias(libs.plugins.yamato.android.library)
    alias(libs.plugins.yamato.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "jp.shirataki707.yamato.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.okhttp.logging)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}
