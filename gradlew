#!/usr/bin/env sh

set -eu

if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi

echo "Gradle is not installed on this system." >&2
echo "Install Gradle 8.14+ or open this project in Android Studio and use its bundled Gradle support." >&2
exit 1
