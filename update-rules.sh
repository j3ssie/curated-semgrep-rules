#!/bin/bash

cloning() {
    echo -e "\033[1;37m[\033[1;31m+\033[1;37m] Cloning\033[1;32m $1 \033[0m"
}

rm -rf rules && mkdir -p rules > /dev/null 2>&1

cloning "https://gitlab.com/gitlab-org/security-products/sast-rules"
git clone --quiet --depth=1 https://gitlab.com/gitlab-org/security-products/sast-rules rules/gitlab
rm -rf rules/gitlab/ci rules/gitlab/mappings

cloning "https://github.com/trailofbits/semgrep-rules"
git clone --quiet --depth=1 https://github.com/trailofbits/semgrep-rules rules/trailofbits

cloning "https://github.com/Decurity/semgrep-smart-contracts"
git clone --quiet --depth=1 https://github.com/Decurity/semgrep-smart-contracts rules/semgrep-smart-contracts

cloning "https://github.com/0xdea/semgrep-rules"
git clone --quiet --depth=1 https://github.com/0xdea/semgrep-rules rules/0xdea

cloning "https://github.com/kondukto-io/semgrep-rules"
git clone --quiet --depth=1 https://github.com/kondukto-io/semgrep-rules rules/kondukto

cloning "https://github.com/ligurio/semgrep-rules"
git clone --quiet --depth=1 https://github.com/ligurio/semgrep-rules rules/tmp-ligurio
mv rules/tmp-ligurio/rules rules/ligurio

cloning "https://github.com/mindedsecurity/semgrep-rules-android-security"
git clone --quiet --depth=1 https://github.com/mindedsecurity/semgrep-rules-android-security rules/android-security

cloning "https://github.com/elttam/semgrep-rules"
git clone --quiet --depth=1 https://github.com/elttam/semgrep-rules rules/tmp-elttam
rm -rf rules/elttam && mkdir -p rules/elttam > /dev/null 2>&1
cp -R rules/tmp-elttam/rules/* rules/tmp-elttam/rules-audit/* rules/elttam/

cloning "https://github.com/dgryski/semgrep-go"
git clone --quiet --depth=1 https://github.com/dgryski/semgrep-go rules/dgryski

cloning "https://github.com/federicodotta/semgrep-rules"
git clone --quiet --depth=1 https://github.com/federicodotta/semgrep-rules rules/federicodotta

cloning "https://github.com/hashicorp-forge/semgrep-rules"
git clone --quiet --depth=1 https://github.com/hashicorp-forge/semgrep-rules rules/hashicorp

rm -rf rules/tmp-* > /dev/null 2>&1
find rules -name ".git" -type d -exec rm -rf {} + > /dev/null 2>&1