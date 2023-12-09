import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.merkost.mv"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
//                implementation(compose.desktop.macos_arm64)
//                implementation(compose.desktop.windows_x64)
                implementation("org.openpnp:opencv:4.8.1-0")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.materialIconsExtended)
                implementation("com.darkrockstudios:mpfilepicker:2.1.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            outputBaseDir.set(project.buildDir.resolve("customOutputDir"))
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "MachineVision"
            packageVersion = "1.0.0"
        }
    }
}
