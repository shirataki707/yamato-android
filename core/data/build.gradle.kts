plugins {
    alias(libs.plugins.yamato.android.library)
    alias(libs.plugins.yamato.hilt)
}

android {
    namespace = "jp.shirataki707.yamayo.core.data"
}

dependencies {
    api(projects.core.database)
}