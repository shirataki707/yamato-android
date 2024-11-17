plugins {
    alias(libs.plugins.yamato.android.feature)
    alias(libs.plugins.yamato.android.library.compose)
}

android {
    namespace = "jp.shirataki707.feature.home"
}

dependencies {
    implementation(projects.core.data)
}
