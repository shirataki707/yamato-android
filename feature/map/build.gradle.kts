plugins {
    alias(libs.plugins.yamato.android.feature)
    alias(libs.plugins.yamato.android.library.compose)
}

android {
    namespace = "jp.shirataki707.feature.map"
}

dependencies {
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(projects.core.data)
    implementation(projects.core.common)

    implementation(libs.androidx.compose.material3)
//    implementation(libs.mapbox)
//    implementation(libs.mapbox.compose)
}
