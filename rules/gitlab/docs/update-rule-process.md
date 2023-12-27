# Updating sast-rules and semgrep


Two projects need to be updated in order to deploy rule changes the first is this one, sast-rules:

1. Checkout `git@gitlab.com:gitlab-org/security-products/sast-rules.git`
2. Create a new feature branch
3. Update the individual rules, or delete
4. Update the CHANGELOG.md to bump to the latest version. Any rule changes must be documented along with reason, and the MR #. Ex: `Update Java rule-CommandInjection.yml to match concatenated strings !169` 
5. Create MR with the labels:  `~devops::secure ~"group::static analysis" ~section::sec ~Category:SAST`
6. Have the MR reviewed and merge when complete

Now we need to update semgrep to use the new rule versions as well as ensure the qa test fixtures are updated to match the state of the changes

1. Checkout `git@gitlab.com:gitlab-org/security-products/analyzers/semgrep.git`
2. Create a new feature branch
3. Modify the [Dockerfile](https://gitlab.com/gitlab-org/security-products/analyzers/semgrep/-/blob/main/Dockerfile?ref_type=heads#L32) to the version of sast-rules that was just updated
4. Modify the [Dockerfile.fips](https://gitlab.com/gitlab-org/security-products/analyzers/semgrep/-/blob/main/Dockerfile.fips?ref_type=heads#L32) to the version of sast-rules that was just updated
5. Run `docker build -t semgrep .` from the project root.
6. Run the integration test locally from the root of the semgrep analyzer project to see if the rule changes caused the qa expectations to now fail (most likely they did). This can take up to 2-3 minutes
```
docker run --platform linux/amd64 -it --rm -v "$PWD:$PWD" -w "$PWD" \
  -e TMP_IMAGE=semgrep:latest \
  -v /var/run/docker.sock:/var/run/docker.sock \
  registry.gitlab.com/gitlab-org/security-products/analyzers/integration-test:stable rspec
```
7. If no failures occurred go ahead and update the CHANGELOG, something like:
```
- Update [sast-rules](https://gitlab.com/gitlab-org/security-products/sast-rules/-/tags/v1.3.XX) version 1.3.XX (!294)`
- v1.3.XX
  - Update Java rule-CommandInjection.yml to match concatenated strings !169
```
8. If failures did occur, then we will need to update the expectations using [analyzer-scripts](https://gitlab.com/gitlab-org/secure/tools/analyzer-scripts). 
9. Checkout: `git@gitlab.com:gitlab-org/secure/tools/analyzer-scripts.git`
10. Go back to the semgrep project root, and run: `../analyzer-scripts/analyzer-refresh-expected-json` . This will take another 2-3 minutes
11. You can either re-run step 6 (re-running integration test locally) or simply push your changes and create your MR. Besure to update the CHANGELOG as in step 7.
12. Assign a [CODEOWNER](https://gitlab.com/gitlab-org/security-products/analyzers/semgrep/-/blob/main/.gitlab/CODEOWNERS?ref_type=heads) to review. 
13. :party:
