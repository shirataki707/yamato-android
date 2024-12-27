plugins {
    alias(libs.plugins.yamato.android.library)
    alias(libs.plugins.yamato.hilt)
    alias(libs.plugins.junit5)
}

android {
    namespace = "jp.shirataki707.yamayo.core.data"
}

dependencies {
    api(projects.core.network.yamato)
    api(projects.core.network.youtube)
    api(projects.core.database)

    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
}
