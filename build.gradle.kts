plugins {
  kotlin("js") version "1.4.21"
}

group = "io.jamesbarnett"
version = "1.0-SNAPSHOT"

repositories {
  jcenter()
  mavenCentral()
}

kotlin {
  js(LEGACY) {
    browser {
      testTask {
        enabled = false
      }
      binaries.executable()
    }
  }
}