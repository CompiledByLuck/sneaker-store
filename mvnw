#!/bin/sh
# Lightweight Maven bootstrap launcher for this project.
# It downloads Maven 3.9.9 once and caches it in ~/.m2/wrapper/dists.
set -eu

MAVEN_VERSION="3.9.9"
MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"
CACHE_DIR="${MAVEN_USER_HOME}/wrapper/dists/apache-maven-${MAVEN_VERSION}"
MAVEN_HOME="${CACHE_DIR}/apache-maven-${MAVEN_VERSION}"
MAVEN_BIN="${MAVEN_HOME}/bin/mvn"

if [ ! -x "${MAVEN_BIN}" ]; then
    mkdir -p "${CACHE_DIR}"
    ARCHIVE="${CACHE_DIR}/apache-maven-${MAVEN_VERSION}-bin.zip"
    URL="https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.zip"

    if command -v curl >/dev/null 2>&1; then
        curl -fL "${URL}" -o "${ARCHIVE}"
    elif command -v wget >/dev/null 2>&1; then
        wget -O "${ARCHIVE}" "${URL}"
    elif command -v mvn >/dev/null 2>&1; then
        exec mvn "$@"
    else
        echo "Не знайдено curl/wget або встановленого Maven. Встановіть Maven 3.9.9 чи забезпечте доступ до Інтернету." >&2
        exit 1
    fi

    if command -v unzip >/dev/null 2>&1; then
        unzip -q -o "${ARCHIVE}" -d "${CACHE_DIR}"
    elif command -v python3 >/dev/null 2>&1; then
        python3 -c 'import sys, zipfile; zipfile.ZipFile(sys.argv[1]).extractall(sys.argv[2])' "${ARCHIVE}" "${CACHE_DIR}"
    else
        echo "Для розпакування Maven потрібен unzip або Python 3." >&2
        exit 1
    fi
    rm -f "${ARCHIVE}"
fi

exec "${MAVEN_BIN}" "$@"
