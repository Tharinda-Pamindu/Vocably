# Vocably

 AI-assisted vocabulary notebook for Android that lets learners capture new words, generate concise meanings with Gemini, and review them in a clean Material 3 interface with light/dark theming plus built-in English text-to-speech.

## Project Description
 Vocably streamlines vocabulary building: users authenticate with Firebase, add words locally with Room, optionally generate a short description via the Gemini API before saving, and hear pronunciations with English text-to-speech. Words are shown in a RecyclerView-driven list with detail views, dark mode support, and a one-time onboarding flow to keep the experience lightweight.

## Features
- Email/password authentication (Firebase Authentication)
- One-time onboarding screen that hands off to login
- Add, generate (Gemini), view, and manage saved words
- Hear pronunciations via English text-to-speech from list items and detail screens
- Local persistence with Room (`VocabularyDB`, `WordDao`)
- RecyclerView list with bottom sheet add/edit UX and empty-state messaging
- Material 3 theming with light/dark palettes and Glide-driven media

## Technologies Used
- Android SDK with Kotlin/Java (Java 17 toolchain)
- Firebase Authentication
- Room (SQLite) for local storage
- Google Generative AI (Gemini client `com.google.ai.client.generativeai`)
- Glide for GIF/image loading
- Material 3 components, ConstraintLayout, RecyclerView
- JUnit, Espresso for testing scaffolding

## Project Structure
- `app/src/main/java/com/example/vocably/` — activities, adapters, data layer, Gemini client
- `app/src/main/res/layout/` — screens and bottom sheets
- `app/src/main/res/values/` — colors, themes; `values-night/` for dark mode
- `assets/config.properties` — Gemini API key (not in VCS)
- `google-services.json` — Firebase config

## Prerequisites
- Android Studio Hedgehog/Koala or newer
- JDK 17 (Gradle wrapper enforces toolchain)
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
- Tap the speaker icon in the list or detail view to hear the pronunciation.
- Tap a word card to view details (ensure navigation wiring in `WordAdapter`/`ViewWord`).

## Configuration Notes
- Gemini model set to `gemini-1.5-flash` in `GeminiLLM`.
- Dark mode palettes in `values-night/colors.xml` and `values-night/themes.xml`.
- Avoid hardcoded colors; use theme attrs and palette resources.

## Team
- [D.T.P.D.Wickramasinghe ICT/2022/100 5703](https://github.com/Tharinda-Pamindu)
- [D.D.S.S.Kumasaru ICT/2022/099 5702](https://github.com/Dilakshi13)
- [H.M.S.H.K.Aberathna ICT/2022/098 5701](https://github.com/shiranihansika)

## Troubleshooting
- Gemini errors: verify API key file, network, and model name; check Logcat tag `GeminiLLM`.
- Firebase auth issues: confirm email/password sign-in is enabled and `google-services.json` matches the app ID.
- Black screen/crash: check Logcat for missing view IDs or null bindings; ensure launcher activity in `AndroidManifest.xml`.

## License
MIT License (see `LICENSE`).

