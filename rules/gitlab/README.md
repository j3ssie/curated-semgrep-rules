# Semgrep rules

This is the central Semgrep rule repository that hosts the Semgrep rules
for the [GitLab semgrep analyzer](https://gitlab.com/gitlab-org/security-products/analyzers/semgrep).

We follow the testing methodology laid out in this [blog post](https://about.gitlab.com/blog/2021/09/08/write-vulnerability-detection-rules/).

The repository is structured as illustrated below:

``` bash
.
├── mappings
│   └── analyzer.yml
├── dist
│   └── pack.yml
├── c
│   ├── buffer
│   │   ├── rule-strcpy.yml
│   │   ├── test-strcpy.c
│   │   ├── rule-memcpy.yml
│   │   └── test-memcpy.c
│   └── ...
└── javascript
│   └── ...
└── python
│    ├── assert
│    │   ├── rule-assert.yml
│    │   └── test-assert.py
│    └── exec
│    │   ├── rule-exec.yml
│    │   ├── test-exec.yml
│    │   ├── rule-something.yml
│    │   └── test-something.yml
│    └── permission
│    │   ├── rule-chmod.yml
│    │   └── test-chmod.py
│    └── ...
└── ...
```

The structure above follows the pattern:
`<language>/<ruleclass>/{rule-<rulename>.yml, test-<rulename>\..*}` where
`language` denotes the target programming language, `<ruleclass>` is a
descriptive name for the class of issues the rule aims to detect and
`<rulename>` is a descriptive name for the actual rule.

We can have multiple test cases per rule (all prefixed with `test-`) and rule
files `rule-<rulename>.yml` that are prefixed with `rule-`; a rule file
contains a single Semgrep rule.

The `mappings` and `dist` directories include the rule-pack configuration which
define the rules that should included into rule-packs and the resulting,
assembled rule-packs.

## Updating rules

Please see [our update process for more details](docs/update-rule-process.md).

## Makefile

The [Makefile](Makefile) defines a few targets that are helpful when working on rules:

```console
$ make help
TARGETS:
  test                  test all rules with Semgrep
  watch                 watch for file changes and auto-run affected tests
  format                format rule files according to guidelines
  help                  prints this message

```

## Formatting guidelines

Rules contained in this repository have to adhere to the following format:

- Use `"` for strings, otherwise the YAML literal block |
- No collapsing of array elements
- max line-length/text-width: 100 characters
- indentation: 2 spaces
- every rule has to have a corresponding test-case
- if provided, comments-section at the top of the rule file
- every YAML files starts with `---`

### Automatic formatting

`make format` automatically formats/rewrites all the rule files
so that they adhere to our guidelines listed above. Formatting requires the
following ruby gems to be installed:

```console
gem install psych yaml fileutils
```

## Mappings

The mappings directory in this repository contains YAML configuration files
that map native analyzer ids to the corresponding Semgrep rules. These mappings
are digested by the [testing framework](https://gitlab.com/gitlab-org/security-products/sast-rule-testing-framework/rule-testing)
to perform an automated gap analysis; the goal of this analysis is to check
whether there is an unexpected deviation between Semgrep (with the rules in this repository)
and a given analyzer.

In addition to that mappings are also used to automatically assemble
rule-packs. The snippet below illustrates an example mapping files for the
`bandit` analyzer. The `native_id` section includes some information about the
native id mappings. The actual rule mappings are defined in the `mappings`
section. Each mapping defines of which Semgrep rules in this repository, a
bandit rules is composed. Note that the order of the rules in the files are
listed does matter at the moment, so that new mappings should be appended at
the end.

``` yaml
bandit:
  native_id:
    type: "bandit_test_id"
    name: "Bandit Test ID: $ID"
    value: "$ID"
  mappings:
  - id: "B301"
    rules:
      - "python/deserialization/rule-cpickle"
      - "python/deserialization/rule-shelve"
      - "python/deserialization/rule-pickle"
      - "python/deserialization/rule-dill"
  - id: "B101"
  # ...
```

## Data sources

The rules and test-cases in this repository are partially sourced from the
sources listed below:
1. https://github.com/returntocorp/semgrep-rules
1. https://github.com/PyCQA/bandit
1. https://github.com/nodesecurity/eslint-plugin-security
1. https://github.com/jsx-eslint/eslint-plugin-react
1. https://github.com/david-a-wheeler/flawfinder/blob/master/flawfinder.py

The details are listed in the headers of all the rule end test-files including
the licensing information and proper attribution.

## Contributing

If you know about a pattern that isn't present in this repo or refinements that
could be applied to the rules in this repository, you can contribute by opening
an issue, or even submit an improvement to the rule files/test cases in this
repository.

## Contribution instructions

After making changes to rules or mappings, make sure to run `./ci/deploy.sh <semantic version>`
and commit your updates to the `/dist` directory where `<semantic version>`
should correspond to the latest published version in [CHANGELOG.md](./CHANGELOG.md)>

## Versioning and Changelog

We apply the following semantic versioning scheme to this repository:

1. patch version increment: for updated/patched/added rules.
1. minor version increment: backwards-compatible YAML schema changes (e.g., adding/removing optional fields).
1. major version increment: non-backwards-compatible YAML schema changes (e.g., adding/removing required fields)

## Credits

We would like to thank the following authors very much for their valuable
contributions.

| Author            | MRs/Issues |
| ----------------- | ---------- |
| @masakura         | !99, !107  |
| @gregory.mcdaniel | #32        |
| @niklas.volcz.    | !183       |
