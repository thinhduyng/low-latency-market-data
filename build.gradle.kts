plugins {
  application
}

repositories {
  mavenCentral()
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

application {
  mainClass = "com.thinhduynguyen.marketdata.Main"
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
  useJUnitPlatform()
}
