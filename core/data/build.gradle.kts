plugins {
    alias(libs.plugins.yamato.android.library)
}

android {
    namespace = "jp.shirataki707.yamayo.core.data"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}