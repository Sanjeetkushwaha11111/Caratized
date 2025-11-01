# Caratized - Configuration Guide

## Project Overview

**Caratized** is a Kotlin Multiplatform project targeting:

- **Android** (Mobile app)
- **Web** (Browser-based using Compose for Web with JS and WebAssembly)
- **Server** (Ktor backend)

---

## Project Structure

```
Caratized/
├── composeApp/          # Compose Multiplatform UI application
│   ├── src/
│   │   ├── androidMain/    # Android-specific code
│   │   ├── commonMain/     # Shared UI code across all platforms
│   │   ├── commonTest/     # Shared tests
│   │   └── webMain/        # Web-specific code (JS + Wasm)
│   └── build.gradle.kts
│
├── server/              # Ktor server application
│   ├── src/
│   │   ├── main/kotlin/    # Server application code
│   │   └── test/kotlin/    # Server tests
│   └── build.gradle.kts
│
├── shared/              # Shared business logic
│   ├── src/
│   │   ├── commonMain/     # Common shared code
│   │   ├── androidMain/    # Android platform-specific implementations
│   │   ├── jvmMain/        # JVM platform-specific implementations
│   │   ├── jsMain/         # JavaScript platform-specific implementations
│   │   └── wasmJsMain/     # WebAssembly platform-specific implementations
│   └── build.gradle.kts
│
├── gradle/              # Gradle wrapper and version catalog
├── build.gradle.kts     # Root build configuration
├── settings.gradle.kts  # Project settings and modules
└── gradle.properties    # Gradle properties and JVM args
```

---

## Technology Stack

### Core Technologies

- **Kotlin**: 2.2.20
- **Gradle**: 8.14.3
- **JVM**: 11 (target), 21 (development)

### Frameworks & Libraries

- **Compose Multiplatform**: 1.9.1
- **Ktor**: 3.3.1
- **Android Gradle Plugin**: 8.11.2
- **AndroidX Lifecycle**: 2.9.5
- **Logback**: 1.5.20 (for server logging)

### Android Configuration

- **Compile SDK**: 36
- **Target SDK**: 36
- **Min SDK**: 24
- **Namespace**: `com.jini.caratized`
- **Application ID**: `com.jini.caratized`

---

## Prerequisites

### Required Software

1. **JDK 11 or higher** (JDK 21 recommended)
    - Check: `java -version`
    - Download: https://adoptium.net/

2. **Android SDK** (for Android development)
    - Android Studio includes the SDK
    - Or install command-line tools: https://developer.android.com/studio#command-tools
    - SDK Location: `/Users/snapmint/Library/Android/sdk` (configured in `local.properties`)

3. **Git** (for version control)
    - Check: `git --version`

### Optional Tools

- **Android Studio** (recommended for Android development)
- **IntelliJ IDEA** (excellent for multiplatform development)
- **Node.js & Yarn** (automatically managed for web builds)

---

## Initial Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd Caratized
```

### 2. Configure Local Properties

The `local.properties` file (already configured) contains:

```properties
sdk.dir=/Users/snapmint/Library/Android/sdk
```

**Note**: This file is git-ignored and machine-specific. Update the `sdk.dir` path if your Android
SDK is in a different location.

### 3. Verify Gradle Setup

```bash
./gradlew --version
```

Expected output should show:

- Gradle 8.14.3+
- Kotlin 2.0.21+
- JVM 11+

### 4. Sync Dependencies

```bash
./gradlew --refresh-dependencies
```

---

## Building the Project

### Build All Modules

```bash
./gradlew build
```

### Android App

**Assemble Debug APK:**

```bash
./gradlew :composeApp:assembleDebug
```

Output: `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

**Assemble Release APK:**

```bash
./gradlew :composeApp:assembleRelease
```

**Install on Device:**

```bash
./gradlew :composeApp:installDebug
```

### Web Application

**Development Mode (Wasm - recommended):**

```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

Access at: http://localhost:8080

**Development Mode (JS - legacy browser support):**

```bash
./gradlew :composeApp:jsBrowserDevelopmentRun
```

**Production Build (Wasm):**

```bash
./gradlew :composeApp:wasmJsBrowserProductionWebpack
```

**Production Build (JS):**

```bash
./gradlew :composeApp:jsBrowserProductionWebpack
```

### Server Application

**Run Development Server:**

```bash
./gradlew :server:run
```

Default server port: 8080 (configurable in server code)

**Build Distribution:**

```bash
./gradlew :server:installDist
```

Output: `server/build/install/server/`

**Run Distribution:**

```bash
./server/build/install/server/bin/server
```

---

## Running Tests

### All Tests

```bash
./gradlew test
```

### Android Tests

```bash
./gradlew :composeApp:testDebugUnitTest
```

### Server Tests

```bash
./gradlew :server:test
```

### Shared Module Tests

```bash
./gradlew :shared:allTests
```

---

## Development Workflow

### Hot Reload / Live Development

**Android:**

- Use Android Studio's "Run" button
- Changes to code trigger automatic rebuild and app restart

**Web:**

- Run with development task: `./gradlew :composeApp:wasmJsBrowserDevelopmentRun`
- Browser auto-reloads on code changes

**Server:**

- Use Ktor's auto-reload feature (configured in Application.kt)
- Or run with: `./gradlew :server:run -t` (continuous mode)

### IDE Configuration

**IntelliJ IDEA / Android Studio:**

1. Open the project root folder
2. IDE will automatically detect Gradle configuration
3. Wait for Gradle sync to complete
4. Run configurations are pre-configured (if using IDE templates)

**Recommended Plugins:**

- Kotlin Multiplatform Mobile
- Compose Multiplatform IDE Support

---

## Configuration Files Explained

### `gradle.properties`

- JVM memory settings: `-Xmx3072M`
- Kotlin daemon settings
- Android configuration flags
- Gradle build cache and configuration cache enabled

### `gradle/libs.versions.toml`

- Centralized version catalog for all dependencies
- Defines all library versions, dependencies, and plugins
- Easy to update versions in one place

### `settings.gradle.kts`

- Defines project structure and modules
- Configures Maven repositories (Google, Maven Central)
- Enables typesafe project accessors

### Root `build.gradle.kts`

- Applies plugins to subprojects
- No direct implementation, just plugin management

### Module Build Files

- **composeApp/build.gradle.kts**: UI app configuration for Android, JS, Wasm
- **server/build.gradle.kts**: Ktor server JVM application
- **shared/build.gradle.kts**: Shared multiplatform library

---

## Environment Variables

### Optional Environment Variables

**ANDROID_HOME** (alternative to local.properties):

```bash
export ANDROID_HOME=/path/to/android/sdk
```

**JAVA_HOME**:

```bash
export JAVA_HOME=/path/to/jdk
```

---

## Troubleshooting

### Common Issues

**1. "Android SDK not found"**

- Ensure `local.properties` has correct `sdk.dir` path
- Or set `ANDROID_HOME` environment variable
- Install Android SDK via Android Studio or command-line tools

**2. "Gradle sync failed"**

```bash
./gradlew clean --refresh-dependencies
```

**3. "Out of memory during build"**

- Increase heap size in `gradle.properties`:

```properties
org.gradle.jvmargs=-Xmx4096M
```

**4. "Kotlin compiler errors"**

- Ensure using Kotlin 2.2.20+
- Clean build: `./gradlew clean build`
- Invalidate caches in IDE

**5. "Web build fails"**

- Ensure Node.js is installed (for yarn)
- Clean node_modules: `rm -rf build/.gradle/yarn`
- Rebuild: `./gradlew :composeApp:wasmJsBrowserDevelopmentRun --rerun-tasks`

**6. "Server port already in use"**

- Change port in `server/src/main/kotlin/com/jini/caratized/Application.kt`
- Or kill process on port 8080:

```bash
# On macOS/Linux:
lsof -ti:8080 | xargs kill -9

# On Windows:
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

---

## Clean Builds

**Clean all build artifacts:**

```bash
./gradlew clean
```

**Clean specific module:**

```bash
./gradlew :composeApp:clean
./gradlew :server:clean
./gradlew :shared:clean
```

**Full clean (including Gradle cache):**

```bash
./gradlew clean cleanBuildCache
rm -rf .gradle build
```

---

## Gradle Configuration Cache

This project uses Gradle Configuration Cache for faster builds:

- First build may be slower
- Subsequent builds are significantly faster
- Cache stored in `.gradle/configuration-cache/`

**Disable if issues occur:**

```properties
# In gradle.properties
org.gradle.configuration-cache=false
```

---

## Production Deployment

### Android

1. Generate signed APK/AAB using Android Studio
2. Or configure signing in `composeApp/build.gradle.kts`
3. Upload to Google Play Console

### Web

1. Build production bundle: `./gradlew :composeApp:wasmJsBrowserProductionWebpack`
2. Deploy `composeApp/build/dist/wasmJs/productionExecutable/` to web server
3. Configure web server for SPA routing

### Server

1. Build distribution: `./gradlew :server:installDist`
2. Package: `tar -czf server.tar.gz -C server/build/install .`
3. Deploy to server and run: `./server/bin/server`
4. Configure reverse proxy (nginx/Apache) if needed

---

## Additional Resources

- [Kotlin Multiplatform Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/)
- [Ktor Documentation](https://ktor.io/docs/)
- [Kotlin/Wasm](https://kotl.in/wasm/)

---

## Support

For issues specific to:

- **Compose Multiplatform**: [#compose-web Slack](https://slack-chats.kotlinlang.org/c/compose-web)
- **Bug Reports**: [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP)

---

## License

[Add your license here]

---

**Project Status**: ✅ Configured and Ready for Development

Last Updated: November 2025