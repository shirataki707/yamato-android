plugins {
    alias(libs.plugins.yamato.android.library)
    alias(libs.plugins.yamato.android.library.compose)
}

android {
    namespace = "jp.shirataki707.core.ui"
}

dependencies {
    api(projects.core.designsystem)
    api(projects.core.model)
}
