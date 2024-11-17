plugins {
    alias(libs.plugins.yamato.android.library)
    alias(libs.plugins.yamato.hilt)
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "jp.shirataki707.yamato.core.network"

    buildFeatures {
        buildConfig = true
    }
}

secrets {
    val secretsFile = file("secrets.properties")
    defaultPropertiesFileName = if (secretsFile.exists()) {
        "secrets.properties"
    } else {
        "secrets.defaults.properties"
    }

}

dependencies {
    api(projects.core.common)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}