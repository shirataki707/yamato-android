import com.android.build.gradle.LibraryExtension
import jp.shirataki707.yamato.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("yamato.android.library")
                apply("yamato.hilt")
                apply("de.mannodermaus.android-junit5")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("org.jetbrains.kotlin.plugin.parcelize")
            }
            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:designsystem"))

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("androidx.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())

                add("testImplementation", libs.findLibrary("junit.jupiter.api").get())
                add("testImplementation", libs.findLibrary("junit.jupiter.engine").get())
                add("testImplementation", libs.findLibrary("junit.jupiter.params").get())
                add("testImplementation", libs.findLibrary("mockito.core").get())
                add("testImplementation", libs.findLibrary("mockito.kotlin").get())
                add("testImplementation", libs.findLibrary("kotlinx.coroutines.test").get())

//                add("testImplementation", libs.findLibrary("androidx.navigation.testing").get())
//                add("androidTestImplementation", libs.findLibrary("androidx.lifecycle.runtimeTesting").get())
            }
        }
    }
}
