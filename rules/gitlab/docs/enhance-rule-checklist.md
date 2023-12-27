## Enhancing a rule checklist

- [ ] Add a runnable test case demonstrating the vulnerability in one of the [real world test projects](https://gitlab.com/gitlab-org/security-products/tests/sast-rules-apps/)
  - [ ] Add a comment above each method/sink of which rule the test was implemented for.
- [ ] Fix/update rule to the specification in this issue
- [ ] Create a test in the same directory, replacing 'rule-XXX.yml' with 'rule-XXX.java'
    - [ ] If one exists, add the variants or update tests for both true and negative cases. Be sure they follow Semgrep's [rule testing guidelines](https://semgrep.dev/docs/writing-rules/testing-rules/).
- [ ] Update the CHANGELOG.md with the change:
```
## v2.X.X
- Adds/Changes/Updates new pattern to existing rule `java/xss/rule-XXX.yml` for Java that checks XXX for XXX (!<merge request id>) 
...
```
- [ ] Push the changes as a feature branch to this repository if access, otherwise to a fork
- [ ] Create the MR
- [ ] Update the !<merge request id> in the CHANGELOG.md if necessary after the MR is created
- [ ] Assign for review to someone in the [CODEOWNERS](../CODEOWNERS) file

AI can save time by rephrasing rule descriptions and generating test cases, but should not be relied upon for the actual security research work. To align with our [dogfooding](https://handbook.gitlab.com/handbook/values/#dogfooding) value, please use [GitLab Duo](https://about.gitlab.com/gitlab-duo/), which is powered by best-is-class AI models.

### If rule requires copying to a new file:

Let's say you need to split out a new SQL Injection rule from the original [java/inject/rule-SqlInjection.yml](../java/inject/rule-SqlInjection.yml). For example, maybe we want specific recommendations for postgresql.
Assume the SqlInjection check originally came from spotbugs, this means we need to map the rule id in the [mappings/find_sec_bugs.yml](../mappings/find_sec_bugs.yml) file. Since we are adding a new variant that has a custom recommendation, it should be split into it's own file. You'll find that the original id in the `find_sec_bugs.yml` has the the generic `SQL_INJECTION` mapping id (the other `SQL_INJECTION_XXX` are more specific):
```
  - id: "SQL_INJECTION"
    rules:
    - path: "java/inject/rule-SqlInjection"
      primary_id: "find_sec_bugs.SQL_INJECTION-1"
      id: "find_sec_bugs.SQL_INJECTION-1"
```
We would need to do the following additional steps:

- [ ] Create a new rule file called [java/inject/rule-SqlInjection-Postgres.yml](../java/inject/rule-SqlInjection-Postgres.yml).
- [ ] Add our test case: [java/inject/rule-SqlInjection-Postgres.java](../java/inject/rule-SqlInjection-Postgres.java).
  - [ ] Be sure they follow Semgrep's [rule testing guidelines](https://semgrep.dev/docs/writing-rules/testing-rules/).
- [ ] Add our test into the [real world test projects](https://gitlab.com/gitlab-org/security-products/tests/sast-rules-apps/).
  - [ ] Add a comment above each method/sink of which rule the test was implemented for.
- [ ] Update the `find_sec_bugs.yml` file with a new rule entry and increment the identifier by `1` (1 -> 2, 2 -> 3 etc):
```
  - id: "SQL_INJECTION"
    rules:
    - path: "java/inject/rule-SqlInjection"
      primary_id: "find_sec_bugs.SQL_INJECTION-1"
      id: "find_sec_bugs.SQL_INJECTION-1"
    - path: "java/inject/rule-SqlInjection-Postgres" # This part added here.
      primary_id: "find_sec_bugs.SQL_INJECTION-2"
      id: "find_sec_bugs.SQL_INJECTION-2"
```
- [ ] Update the CHANGELOG.md with the change:
```
## v2.X.X
- Adds new rule `java/inject/rule-SqlInjection-Postgres.java` for Java that has custom Postgres recommendations (!<merge request id>) 
...
```
- [ ] Create the MR
- [ ] Update the !<merge request id> in the CHANGELOG.md if necessary after the MR is created
- [ ] Assign for review to someone in the [CODEOWNERS](../CODEOWNERS) file.
