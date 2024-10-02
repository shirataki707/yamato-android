plugins {
    alias(libs.plugins.yamato.android.feature)
    alias(libs.plugins.yamato.android.library.compose)
}

android {
    namespace = "jp.shirataki707.feature.mountain"
}

dependencies {
    debugImplementation(project(path = ":core:data", configuration = "demoDebugRuntimeElements"))

    implementation(libs.androidx.compose.material3)
}