# Calculator M3 Android App

A simple calculator app built with Kotlin, Jetpack Compose, and Material 3.

## What this project includes

- Clean Compose UI with Material 3 styling
- Basic operations: `+`, `-`, `×`, `÷`, `%`
- Utility actions: `AC`, backspace (`⌫`), decimal input
- Operator precedence handling in expression evaluation
- Custom adaptive launcher icon
- Unit tests for calculator engine behavior

## Android configuration

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Design system:** Material 3
- **Min SDK:** 24
- **Target/Compile SDK:** 35
- **Java version:** 17+

## Open and run in Android Studio

1. Clone the repository:

   ```bash
   git clone <your-repo-url>
   cd OIBSIP_AndroidAppDev_3
   ```

2. Open Android Studio (latest stable).
3. Choose **Open** and select the project folder.
4. Let Gradle sync complete.
5. Run on emulator or device.

Android Studio can use its built-in Gradle support. A binary `gradle-wrapper.jar` is intentionally not stored in this repository to keep the repo fully text-only for environments that reject binary files in PR workflows.

## Command-line build

If Gradle is installed locally:

```bash
./gradlew assembleDebug
./gradlew test
```

## Project layout

```text
app/
  src/main/java/com/example/calculator/
    CalculatorEngine.kt
    MainActivity.kt
    ui/theme/
      Theme.kt
      Type.kt
  src/main/res/
    drawable/
    mipmap-anydpi-v26/
    values/
```

## Notes on calculation behavior

- Entering two operators in a row replaces the previous one.
- Decimal input is limited to one decimal point per number segment.
- Trailing-operator expressions return `Error`.
- Results are normalized to remove unnecessary trailing zeros.
