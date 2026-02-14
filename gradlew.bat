@echo off
where gradle >nul 2>nul
if %ERRORLEVEL% == 0 (
  gradle %*
  exit /b %ERRORLEVEL%
)
echo Gradle is not installed on this system.
echo Install Gradle 8.14+ or open this project in Android Studio and use its bundled Gradle support.
exit /b 1
