# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

HushKeyboard is a specialized Android Input Method Editor (IME) that provides a custom keyboard for typing Rubik's Cube notations. The app makes it easier for cubers to input cube algorithms with dedicated notation keys, modifiers, and gesture support.

## Development Commands

### Building and Testing
```bash
# Build the project
./gradlew build

# Run unit tests
./gradlew test
./gradlew testDebugUnitTest
./gradlew testReleaseUnitTest

# Run a specific test class
./gradlew test --tests "com.rickyhu.hushkeyboard.model.CubeKeyTest"

# Run instrumentation tests (requires connected device/emulator)
./gradlew connectedDebugAndroidTest

# Code quality checks
./gradlew ktlintCheck
./gradlew ktlintFormat
./gradlew lint
./gradlew check

# Coverage reports
./gradlew koverHtmlReport
./gradlew koverXmlReport
```

### Development Setup
```bash
# Clean build
./gradlew clean

# Install debug APK
./gradlew installDebug
```

## Architecture Overview

### Core Architecture Pattern
- **MVVM with Repository Pattern**: ViewModels manage UI state, Repositories handle data operations
- **Jetpack Compose**: Modern declarative UI framework for all screens
- **Dagger Hilt**: Dependency injection throughout the app
- **DataStore**: Settings persistence using kotlinx.serialization
- **Type-safe Navigation**: Navigation Compose with serializable route objects

### Key Components

#### IME Service Layer
- `HushIMEService`: Main IME service that extends `LifecycleInputMethodService`
- `LifecycleInputMethodService`: Custom base class that adds lifecycle awareness to InputMethodService
- `HushKeyboardView`: Compose-based keyboard view that renders in the IME context

#### Domain Models
- `CubeKey`: Core domain model representing a Rubik's Cube notation with configuration (turns, wide, counter-clockwise)
- `Notation`: Enum of all supported cube notations (RULFDB, MES, xyz)
- `NotationKeyProvider`: Factory for creating keyboard layouts with proper key configurations

#### Data Layer
- `SettingsRepository`: Manages app settings using DataStore
- `AppSettings`: Serializable data class for user preferences
- `AppSettingsSerializer`: Custom serializer for DataStore integration

#### UI Architecture
- `MainApp`: Root Compose application with theme management
- `MainNavHost`: Type-safe navigation setup with serializable routes
- Screen-specific ViewModels that observe settings from repository
- Keyboard UI composed of button rows with consistent styling

### State Management Flow
1. Settings changes flow through `SettingsRepository`
2. ViewModels observe settings via StateFlow
3. UI recomposes reactively when settings change
4. Both main app and IME keyboard share the same settings source

### IME-Specific Architecture
- `HushKeyboardView` bridges Android's InputMethodService with Jetpack Compose
- Keyboard state management handles notation input, modifiers, and text output
- Input connection utilities handle text insertion and deletion
- Vibration feedback integrated through system services

### Testing Strategy
- Unit tests for domain models (CubeKey logic, notation handling)
- UI tests for Compose screens using ComposeTestRule
- Instrumentation tests for IME integration (manual testing required)

## Development Notes

### Kotlin Version Catalogs
Dependencies are managed in `gradle/libs.versions.toml` using version catalogs. When adding new dependencies, update the TOML file rather than hardcoding versions in build files.

### Code Style
- ktlint enforced via pre-commit hooks (`preBuild` depends on `ktlintFormat`)
- Consistent naming: `Screen` suffix for Compose screens, `ViewModel` suffix for ViewModels
- Hilt injection pattern: `@Inject constructor` for dependencies

### IME Development Considerations
- IME services have different lifecycle than regular activities
- Testing IME functionality requires manual testing on device/emulator
- Keyboard view must handle both light and dark themes
- Input connection management is critical for proper text insertion

### Settings Architecture
- All settings flow through single source of truth (`SettingsRepository`)
- Settings are reactive - changes immediately update both main app and keyboard
- DataStore provides type-safe persistence with automatic serialization

### Cube Notation Domain
- Notation keys support modifiers: turns (single, double), prime (counter-clockwise), wide turns
- Different notation types have different rules (e.g., middle layer moves don't support wide turns)
- Two wide notation formats supported: "Rw" style vs lowercase "r" style