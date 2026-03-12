# Vocably

Lightweight vocabulary manager for Android that lets users store words, generate concise descriptions with Gemini, and view them in a clean Material3 UI with light/dark support.

## Features
- Email/password auth with Firebase Authentication
- Local persistence with Room (`VocabularyDB`, `WordDao`)
- Gemini-powered one-sentence descriptions via `GeminiLLM`
- Add/view words with RecyclerView + BottomSheet
- Material3 theming with light/dark palettes

## Tech Stack
- Kotlin/Java (Android SDK)
- Firebase Authentication (email/password)
- Room (SQLite)
- Google Generative AI client (Gemini)
- Material3 components

## Project Structure
- `app/src/main/java/com/example/vocably/` — activities, adapters, data, Gemini client
- `app/src/main/res/layout/` — screens and bottom sheets
- `app/src/main/res/values/` — colors, themes; `values-night/` for dark mode
- `assets/config.properties` — Gemini API key (not in VCS)
- `google-services.json` — Firebase config

## Prerequisites
- Android Studio Hedgehog/Koala or newer
- JDK 17 (Gradle wrapper handles toolchains)
- Firebase project with email/password auth enabled
- Gemini API key (Generative Language API access)

## Setup
1) Create `app/src/main/assets/config.properties` with:
```properties
GEMINI_API_KEY=your_key_here
```
2) Ensure `app/google-services.json` is present (Firebase download).
3) Sync Gradle in Android Studio.

## Build
Debug APK:
```powershell
cd "D:\RUSL\Third Year\my\ICT3214 - Mobile Application Development\Project\Vocably"
./gradlew assembleDebug
```
Release APK (requires signing config in `app/build.gradle.kts`):
```powershell
./gradlew assembleRelease
```
Outputs: `app/build/outputs/apk/{debug|release}/`.

## Run
- In Android Studio: Run ▶ on a device/emulator (API 24+ recommended).
- Install debug APK manually: `adb install app/build/outputs/apk/debug/app-debug.apk`.

## Testing
- Instrumented/UI tests (if added): `./gradlew connectedAndroidTest`
- Unit tests (if added): `./gradlew test`

## Usage
- Register or log in (Firebase Auth).
- Tap ➕ to add a word. Optionally tap **Generate** to fetch a Gemini description; edit and save.
- Tap a word card to view details (ensure navigation wiring in `WordAdapter`/`ViewWord`).

## Configuration Notes
- Gemini model set to `gemini-1.5-flash` in `GeminiLLM`.
- Dark mode palettes in `values-night/colors.xml` and `values-night/themes.xml`.
- Avoid hardcoded colors; use theme attrs and palette resources.

## Troubleshooting
- Gemini errors: verify API key file, network, and model name; check Logcat tag `GeminiLLM`.
- Firebase auth issues: confirm email/password sign-in is enabled and `google-services.json` matches the app ID.
- Black screen/crash: check Logcat for missing view IDs or null bindings; ensure launcher activity in `AndroidManifest.xml`.

## License
Proprietary/internal (add your license here as needed).

