# Bio Lung – Android app

Kotlin + WebView wrapper around the Bio Lung dashboard (`app/src/main/assets/index.html`, fully offline).
The app launches in **DEMO** mode (simulated data). **REAL** mode unlocks automatically once the
ESP32 at `http://192.168.4.1` is reachable.

- Package `com.biolung.app`, minSdk 26, targetSdk 35, no AndroidX dependencies.
- The process is bound to the Wi-Fi network (`bindProcessToNetwork`) so the internet-less
  **Bio Lung** hotspot works while mobile data stays on.

## Build the APK

Requirements: JDK 17 and the Android SDK (platform 35). Android Studio provides both.

### Option A – Android Studio
1. **File ▸ Open…** and choose the `BioLungAndroid` folder; let Gradle sync finish.
2. **Build ▸ Build Bundle(s) / APK(s) ▸ Build APK(s)**.
3. Output: `app/build/outputs/apk/debug/app-debug.apk`.

### Option B – command line
```
cd BioLungAndroid
# Windows
gradlew.bat assembleDebug
# macOS / Linux (first run: chmod +x gradlew)
./gradlew assembleDebug
```
If Gradle cannot find the SDK, create `local.properties` containing `sdk.dir=C\:\\Users\\<you>\\AppData\\Local\\Android\\Sdk`.

The build also copies the APK to **`BioLungAndroid/BioLung.apk`**.

## Install
Copy `BioLung.apk` to the phone and open it (allow "install unknown apps"), or run
`adb install -r BioLung.apk`.

## Using Real mode
1. Flash the updated `BioLung.ino` (adds the CORS header).
2. Join Wi-Fi **Bio Lung** (password `Camb@2026`). If Android asks to stay connected without internet, choose **Yes / Keep**.
3. Within ~3 s the header shows **ESP32 Ready** and **REAL** unlocks. Tap it.
4. If 3 polls fail in a row the app shows "ESP32 Disconnected" and returns to DEMO.
