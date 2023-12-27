# Curated Collection of Popular Community Rules for Semgrep

This repository aims to provide a comprehensive set of effective Semgrep rules that have been contributed and vetted by the community.

## Usage

```bash
# udpate the rules
./update-rules.sh

# validate all the rules
semgrep scan -j 100 -v --config ./rules --validate .

# actually running the scan
# '--config r/default' meaning that we already included the public rules of Semgrep
semgrep scan -j 100 --config r/default --config ./rules vulnerable-source-code
```

## Special Thanks

Special thanks to the following repositories whose contributions have been instrumental in curating this collection of Semgrep rules:

- [Gitlab SAST rules](https://gitlab.com/gitlab-org/security-products/sast-rules)
- [Semgrep Commmunity rules](https://github.com/semgrep/semgrep-rules)
- [TrailofBits](https://github.com/trailofbits/semgrep-rules)
- [Decurity](https://github.com/Decurity/semgrep-smart-contracts)
- [0xdea](https://github.com/0xdea/semgrep-rules)
- [kondukto](https://github.com/kondukto-io/semgrep-rules)
- [ligurio](https://github.com/ligurio/semgrep-rules)
- [mindedsecurity](https://github.com/mindedsecurity/semgrep-rules-android-security)
- [elttam](https://github.com/elttam/semgrep-rules)
- [dgryski](https://github.com/dgryski/semgrep-go)
- [federicodotta](https://github.com/federicodotta/semgrep-rules)
- [hashicorp](https://github.com/hashicorp-forge/semgrep-rules)

## Disclaimer

While these rules are developed and shared with the intent of improving code quality and security, they might not cover every possible scenario. Always exercise caution and perform comprehensive testing in conjunction with other security measures.
