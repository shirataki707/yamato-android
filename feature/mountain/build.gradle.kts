plugins {
    alias(libs.plugins.yamato.android.feature)
    alias(libs.plugins.yamato.android.library.compose)
}

android {
    namespace = "jp.shirataki707.feature.mountain"
}

dependencies {
    api(projects.core.designsystem)

    implementation(projects.core.data)
    implementation(projects.core.common)

    implementation(libs.androidx.compose.material3)
}