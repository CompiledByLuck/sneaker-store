@echo off
setlocal
set "MAVEN_VERSION=3.9.9"
set "CACHE_DIR=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%"
set "MAVEN_HOME=%CACHE_DIR%\apache-maven-%MAVEN_VERSION%"
set "MAVEN_EXE=%MAVEN_HOME%\bin\mvn.cmd"

if not exist "%MAVEN_EXE%" (
    if not exist "%CACHE_DIR%" mkdir "%CACHE_DIR%"
    echo Завантаження Apache Maven %MAVEN_VERSION% (лише під час першого запуску)...
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$ErrorActionPreference='Stop'; $cache='%CACHE_DIR%'; $zip=Join-Path $cache 'apache-maven-%MAVEN_VERSION%-bin.zip'; Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip' -OutFile $zip; Expand-Archive -LiteralPath $zip -DestinationPath $cache -Force; Remove-Item $zip -Force"
    if errorlevel 1 (
        echo Не вдалося завантажити Maven. Перевірте Інтернет або встановіть Maven вручну.
        exit /b 1
    )
)

call "%MAVEN_EXE%" %*
exit /b %ERRORLEVEL%
