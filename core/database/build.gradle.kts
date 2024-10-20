plugins {
    alias(libs.plugins.yamato.android.library)
    alias(libs.plugins.yamato.android.room)
    alias(libs.plugins.yamato.hilt)
}

android {
    namespace = "jp.shirataki707.yamato.core.database"
}

dependencies {
    api(projects.core.model)
}
