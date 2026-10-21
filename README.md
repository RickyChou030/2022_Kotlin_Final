# Math Wordle — Equation-Guessing Game for Android

![Kotlin](https://img.shields.io/badge/Kotlin-Android-purple)
![Gradle](https://img.shields.io/badge/Build-Gradle-green)

A math version of **Wordle** for Android, written in Kotlin. Instead of guessing a five-letter word, the player guesses a hidden **arithmetic equation**. After each guess, every tile is colored to show how close the guess is, and the player uses that feedback to narrow down the answer.

Final project for Kotlin Programming (Fall 2022).

**▶ [Watch the demo video](https://youtu.be/nvbtlDtt6lk)**

<!-- TODO: add 1–2 screenshots of the game screen -->

---

## How to Play

1. Enter an equation made of digits and operators. <!-- TODO: equation length, allowed operators (+ − × ÷ =), number of attempts -->
2. Submit the guess. Each tile is colored:

| Color | Meaning |
| --- | --- |
| 🟩 Green | Correct character in the correct position |
| 🟨 Yellow | Character is in the equation, but in a different position |
| ⬜ Gray | Character is not in the equation |

3. Find the hidden equation before running out of attempts.

<!-- TODO: confirm the color rules, and mention any extra rules (e.g. guesses must be mathematically valid) -->

---

## Getting Started

**Requirements:** Android Studio, an Android emulator or a physical device.

1. Clone the repository and open the folder in Android Studio.
2. Let Gradle sync the project.
3. Select an emulator or connected device and click **Run**.

## Project Structure

```
2022_Kotlin_Final/
├── app/               # Android app module (Kotlin source, layouts, resources)
├── gradle/wrapper/    # Gradle wrapper
├── build.gradle       # Project-level build configuration
└── settings.gradle
```

## Tech Stack

- **Language:** Kotlin
- **Platform:** Android SDK
- **Build:** Gradle

---

## Author

**Ricky Chou** · [@RickyChou030](https://github.com/RickyChou030)
