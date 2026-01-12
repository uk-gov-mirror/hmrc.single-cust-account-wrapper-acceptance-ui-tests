#!/usr/bin/env bash
set -e

echo "▶ Running full test suite..."

BROWSER=${1:-chrome}
ENV=${2:-local}

FAILED_ON_RERUN=0

sbt clean -Dbrowser=$BROWSER -Denv=$ENV -Dbrowser.logging=true "testOnly scalaTest.specs.* -- -C scalaTest.utils.ColorfulReporter -oD" testReport | tee >(sed -r "s/\x1B\[[0-9;]*[mK]//g" > test-output-clean.log) || true

grep "\*\*\* FAILED \*\*\*" test-output-clean.log \
  | sed -E 's/.*Scenario: (.*) \*\*\* FAILED \*\*\*.*/\1/' \
  | sort -u \
  > failed-scenarios.txt

if [ ! -s failed-scenarios.txt ]; then
  echo "✅ All tests passed on first run"
  exit 0
fi

echo "🔁 Rerunning FAILED scenarios (ONE retry only)..."

while IFS= read -r scenario; do
  echo "➡ Rerunning scenario: $scenario"

  if ! sbt \
      -Dbrowser=chrome \
      -Denv=local \
      "testOnly uk.gov.hmrc.ui.scalatests.specs.* -- -z \"$scenario\""
  then
    echo "❌ Scenario FAILED again: $scenario"
    FAILED_ON_RERUN=1
  else
    echo "✅ Scenario passed on rerun: $scenario"
  fi
done < failed-scenarios.txt

  if [ "$FAILED_ON_RERUN" -eq 1 ]; then
    echo "❌ Some scenarios failed even after one rerun"
    exit 1
  else
    echo "🎉 All failed scenarios passed on rerun"
    exit 0
  fi