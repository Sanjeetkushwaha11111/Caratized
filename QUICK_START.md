# Quick Start Guide - Caratized

Get your Kotlin Multiplatform project running in minutes!

## Prerequisites Checklist

- [ ] JDK 11+ installed (`java -version`)
- [ ] Android SDK installed (for Android development)
- [ ] Git installed

## Quick Start Commands

### 1. Verify Setup

```bash
./gradlew --version
```

### 2. Build Project

```bash
./gradlew build
```

### 3. Run Your Platform

#### Android App

```bash
# Build and install on connected device/emulator
./gradlew :composeApp:installDebug

# Or open in Android Studio and click Run
```

#### Web App (Modern Browsers - Wasm)

```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

Then open: http://localhost:8080

#### Web App (Legacy Browsers - JS)

```bash
./gradlew :composeApp:jsBrowserDevelopmentRun
```

#### Server

```bash
./gradlew :server:run
```

Server runs on: http://localhost:8080

## Common Tasks

### Run Tests

```bash
./gradlew test
```

### Clean Build

```bash
./gradlew clean build
```

### Check for Updates

```bash
./gradlew dependencyUpdates
```

## Project Structure (Quick Reference)

```
composeApp/     → UI code (Android, Web)
  src/commonMain/   → Shared UI across platforms
  src/androidMain/  → Android-specific UI
  src/webMain/      → Web-specific UI (JS + Wasm)

server/         → Backend server (Ktor)
  src/main/kotlin/  → Server code

shared/         → Business logic shared everywhere
  src/commonMain/   → Platform-agnostic code
  src/androidMain/  → Android-specific implementations
  src/jvmMain/      → JVM-specific implementations
  src/jsMain/       → JS-specific implementations
  src/wasmJsMain/   → Wasm-specific implementations
```

## Technology Stack

- **Kotlin**: 2.2.20
- **Compose Multiplatform**: 1.9.1
- **Ktor**: 3.3.1
- **Gradle**: 8.14.3
- **Android SDK**: Min 24, Target 36

## Need Help?

- 📖 Full documentation: `CONFIGURATION.md`
- 🐛 Issues: Check troubleshooting section in `CONFIGURATION.md`
- 💬 Community: [Kotlin Slack](https://slack-chats.kotlinlang.org/c/compose-web)

## Quick Troubleshooting

### Android SDK not found?

Update `local.properties`:

```properties
sdk.dir=/path/to/your/android/sdk
```

### Port 8080 already in use?

Kill the process:

```bash
# macOS/Linux
lsof -ti:8080 | xargs kill -9

# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### Build failing?

```bash
./gradlew clean --refresh-dependencies
./gradlew build
```

---

**Ready to code!** 🚀

For detailed configuration, deployment, and advanced topics, see `CONFIGURATION.md`.