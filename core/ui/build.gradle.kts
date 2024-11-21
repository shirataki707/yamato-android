plugins {
    alias(libs.plugins.yamato.android.library)
    alias(libs.plugins.yamato.android.library.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "jp.shirataki707.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.coil.kt.compose)
    implementation(libs.bundles.compose.destinations.bundle)

    ksp(libs.compose.destinations.ksp)
}
