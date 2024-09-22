package jp.shirataki707.yamato

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class YamatoBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
