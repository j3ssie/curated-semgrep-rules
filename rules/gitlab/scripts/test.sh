#!/usr/bin/env bash

set -euo pipefail

semgrep_cli="${SEMGREP_CLI:-semgrep}"

if ! command -v "$semgrep_cli" > /dev/null 2>&1; then
  echo "Semgrep CLI is not installed or not available in your PATH."
  echo "Go here for installation instructions: https://semgrep.dev/docs/getting-started/quickstart/"
  exit 1
fi

"$semgrep_cli" --metrics=off --test ./
