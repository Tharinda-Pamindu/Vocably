# Contributing to Vocably

Thanks for taking the time to contribute! Follow these guidelines to keep changes smooth and reviewable.

## Getting Started
- Use Android Studio Hedgehog/Koala or newer and JDK 17.
- Copy `app/google-services.json` and create `app/src/main/assets/config.properties` with `GEMINI_API_KEY=...`.
- Install dependencies via Gradle sync.

## Development Workflow
- Branch from `main` and use clear branch names (e.g., `feature/dark-mode-toggle`, `fix/login-crash`).
- Keep changes focused and small; include relevant tests if you add logic.
- Ensure `./gradlew assembleDebug` and `./gradlew test` succeed before opening a PR.

## Code Style
- Follow Android/Kotlin/Java best practices; prefer Material3 components and theme colors (no hardcoded hex).
- Keep strings and colors in resources; avoid blocking calls on the main thread.
- Add concise comments only where intent isn’t obvious.

## Commit Messages
- Use present tense and a short summary (e.g., `Add Gemini error handling`).

## Pull Requests
- Describe the change, testing performed, and any screenshots/GIFs for UI updates.
- Call out any known issues or follow-ups.

## Reporting Issues
- Provide steps to reproduce, expected vs. actual behavior, device/OS info, and relevant Logcat snippets.

