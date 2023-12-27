sast-rules changelog

## v2.0.9

- Update `java/cookie/rule-CookieHTTPOnly.yml` to support jakarta servlet (!277)
- Removed `java/xss/rule-XSSReqParamToSendError.yml` as sendError is now automatically encoded and this was a bug (CVE-2008-1232) fixed in Apache Tomcat 6 in 2008 (!276)

## v2.0.8

- Update `go/sql/rule-concat-sqli.yml` to cover more cases and merge it with `go/sql/rule-format-string-sqli.yml` (!272)
- Update `go/injection/rule-ssrf.yml` to remove false-positives (!273)
- Update `python/exec/rule-subprocess-popen-shell-true.yml` to remove false-positives (!274)
- Update `python/sql/rule-hardcoded-sql-expression.yml` to remove false-positives (!278)

## v2.0.7

- Update Java LDAP injection rule (!255)
  - `java/inject/rule-LDAPInjection.yml` - Removed the java.util.Properties.Put() sink
  - `java/inject/rule-LDAPInjection.yml` - To match classes that are not fully qualified in imports
- Update `java/script/rule-SpelExpressionParser.yml` to also match parseRaw() injections (!254)
- Update `java/strings/rule-ModifyAfterValidation.yml` to match `replaceAll`, `replaceFirst` & `concat` as possible sinks (!246)
- Rename `java/script/rule-SpelExpressionParser.yml` to `rule-SpringSpelExpressionParser.yml` to avoid naming collision (!263)
- Update `java/cors/rule-PermissiveCORSInjection.yml` with additional sinks (!264)
- Update the existing rule `rules/lgpl/javascript/database/rule-node_sqli_injection.yml` to add support for sequelize, optimize existing patterns and cover more sql cases. (!261)


## v2.0.6

- Update `java/crypto/rule-RsaNoPadding.yml` to eliminate NoPadding false-positives when RSA is not being used (!249)

## v2.0.5

- Fix typos and language (!238)
  - `c/format/rule-snprintf-vsnprintf.yml` - Expand `$SIZ` pattern variable to `$SIZE`
  - `c/obsolete/rule-gsignal-ssignal.yml` - Use of the American English spelling for 'signaling'
  - `java/cors/rule-PermissiveCORSInjection.yml` - `getParameter` method typo in match pattern
  - `java/inject/rule-SqlInjection.yml` - `update` method typo in match pattern
  - `java/password/rule-HardcodeKeyEquals.yml` - `compare` method typo in match pattern
  - `java/strings/rule-NormalizeAfterValidation.yml` - Typo in description
  - `scala/cors/rule-PermissiveCORSInjection.yml` - `getParameter` method typo in match pattern
  - `scala/inject/rule/SqlInjection.yml` - `update` method typo in match pattern
  - `scala/password/rule-HardcodeKeyEquals.yml` - `compare` method typo in match pattern

## v2.0.4

- Update Go Path Traversal Rule (!235)
  - `go/filesystem/rule-filereadtaint.yml` - Reads via `os.ReadFile` will now be detected as part of CWE-22
  - `go/filesystem/rule-filereadtaint.yml` - Remove `filepath.Rel` from pattern exclusions as it doesn't provide a reliable way to mitigate path traversal

## v2.0.3

- Fix incorrect OWASP Top 10 category references in rules: (!234)
  - `python/escaping/rule-django.yml` - `A7:2017-Cross-Site Scripting (XSS)`
  - `python/exec/rule-start-process-partial-path.yml` - `A1:2017-Injection`
  - `python/exec/rule-start-process-path.yml` - `A1:2017-Injection`
  - `python/exec/rule-subprocess-call-array.yml` - `A1:2017-Injection`

## v2.0.2

- Remove Java Rules (!220)
  - `java/cookie/rule-CookiePersistent.yml` - Cookies may not contain sensitive information and should be removed to be consistent with C# rules
  - `java/cookie/rule-CookieUsage.yml` - Cookies may not contain sensitive information and should be removed to be consistent with C# rules
  - `java/cookie/rule-RequestParamToCookie.yml` - Duplicate rule of `rule-HttpResponseSplitting.yml`
  - `java/cookie/rule-TrustBoundaryViolation.yml` - Unnecessary, prone to false positives
  - `java/cors/rule-PermissiveCORS.yml` - The impact of setting \* in a CORS response is minimal, since credentials will not be sent
  - `java/crypto/rule-DefaultHTTPClient.yml` - While Apache client is deprecated, the default client will connect to a TLS1.3 only server
  - `java/endpoint/rule-UnencryptedSocket.yml` - Using a non-TLS socket is perfectly acceptable in many circumstances
  - `java/endpoint/rule-InsecureServlet.yml` - It's perfectly acceptable to access the data from these methods. Additionally, there is no way a customer could 'fix' this
  - `java/endpoint/rule-JaxRsEndpoint.yml` - Incomplete rule, original [SpotBugs rule](https://find-sec-bugs.github.io/bugs.htm#JAXRS_ENDPOINT) is too broad and prone to false positives
  - `java/endpoint/rule-JaxWsEndpoint.yml` - Incomplete rule, original [SpotBugs rule](https://find-sec-bugs.github.io/bugs.htm#JAXWS_ENDPOINT) is too broad and prone to false positives
  - `java/file/rule-FileUploadFileName.yml` - This is a source not a sink
  - `java/form/rule-FormValidate.yml` - ActionForm/ValidatorForm is from Struts 1.1, which was EoL'd 2013
  - `java/inject/rule-AWSQueryInjection.yml` - SimpleDB, while still technically supported, is deprecated and no longer available to new accounts
  - `java/inject/rule-BeanPropertyInjection.yml` - Apache common collections 3 is no longer available and only works on Java 1.3
  - `java/inject/rule-CustomInjectionSQLString.yml` - Prone to false positives and rules do not necessarily match variables that will be used in a SQL query
  - `java/inject/rule-PathTraversalIn.yml` - Logic handled better by `rule-SpotbugsPathTraversalAbsolute.yml`
  - `java/inject/rule-PathTraversalOut.yml` - Logic handled better by `rule-SpotbugsPathTraversalAbsolute.yml`
  - `java/ldap/rule-EntryPoisoning.yml` - $SCOPE could legitimately have a value, logic handled better by `inject/rule-LDAPInjection`
  - `java/password/rule-HardcodeKeySuspiciousName.yml` - Secrets scanning should be used instead
  - `java/password/rule-HardcodeKeySuspiciousValue.yml` - Secrets scanning should be used instead
  - `java/perm/rule-OverlyPermissiveFilePermissionObj.yml` - Logic handled better by `java/perm/rule-OverlyPermissiveFilePermissionInline.yml`
  - `java/strings/rule-ImproperUnicode.yml` - Code quality issue more than a security issue
  - `java/unsafe/rule-InformationExposure.yml` - Printing stack trace information to the local machine is perfectly acceptable
  - `java/unsafe/rule-InformationExposureVariant2.yml` - Printing stack trace information to the local machine is perfectly acceptable
  - `java/xml/rule-ApacheXmlRpc.yml` - Apache Xml RPC was deprecated in 2013
  - `java/xss/rule-RequestWrapper.yml` - Appears to be a custom rule, `stripXSS()` is not a valid override
  - `java/xss/rule-XSSServlet.yml` - Duplicate of `java/xss/rule-XSSReqParamToServletWriter.yml`
  - `java/xss/rule-XSSServletParameter.yml` This is a source not a sink
  - `java/xxe/rule-XPathXXE.yml` - Rule matches a hardcoded variable name, and has no namespace/import associated with it. Better XXE rule required
  - `java/xxe/rule-Trans.yml` - Duplicate of `java/xml/rule-XsltTransform.yml` with less information

## v2.0.1

- Update JavaScript `rule-non-literal-regexp.yml` to filter out usage of RegExp literals (!233)
- Update JavaScript `rule-non-literal-regexp.yml` to match non-constructor `RegExp()` function calls (!233)
- Remove `c/buffer/rule-getpw.yml` - `getpw` function is deprecated in favor of `getpwuid` since 1979 (!229)
- Remove `c/buffer/rule-equal-mismatch.yml` - Rule is for C++ code (!229)
- Rule bug fixes and improvements (!229)
  - `c/buffer/rule-StrCat-StrCatA.c` - Incorrect letter casing in `strcat` pattern
  - `c/buffer/rule-gets-getts.yml` - Add rule for `_getws` function
  - `c/buffer/rule-sprintf-vsprintf.c` - Add `_T` macro to `_tscanf` function pattern

## v2.0.0

- Switch to package registry for releases (!231)

## v1.3.45

- Remove poor C# rules (!218)
  - `csharp/cache/rule-OutputCacheConflicts.yml` - Unable to confirm vulnerability
  - `csharp/other/rule-AuthorizationBypass.yml` - Highly prone to false positives as it assumes any controller without `[AllowAnonymous]` or `[Authorize]` is an authorization bypass

## v1.3.44

- Remove poor JavaScript rules (!219)
  - `javascript/csrf/rule-no_csrf_before_method_override.yml` - Deprecated and no way of testing, see http://blog.nibblesec.org/2014/05/nodejs-connect-csrf-bypass-abusing.html
  - `javascript/react/rule-missing_noopener.yml` - Browsers no longer allow this by default, see https://gitlab.com/gitlab-org/gitlab/-/issues/233079#note_513860690

## v1.3.43

- Remove poor Python rules (!217)
  - `python/cgi/rule-import_httpoxy.yml` - Not vulnerable since 2016 https://bugs.python.org/issue27568
  - `python/crypto/rule-import_pyghmi.yml` - Old rule from 2013 https://www.cisa.gov/news-events/alerts/2013/07/26/risks-using-intelligent-platform-management-interface-ipmi
  - `python/escaping/rule-mark_safe.yml` - Duplicate of `rule-django.yml`
  - `python/exception/rule-try_except_continue.yml` - Not a security rule
  - `python/exception/rule-try_except_pass.yml` - Not a security rule
  - `python/ftp/rule-import_ftplib.yml` - Duplicate rule, see `rule-ftplib.yml`
  - `python/https/rule-httpsconnection.yml` - Software Composition Analysis (SCA) problem, not a SAST problem (flag if python < 3.4.3 and HTTPSConnection is used)
  - `python/secrets/` - enable secret detection instead
  - `python/telnet/rule-telnetlib.yml` - Duplicate of `rule-import_telnib.yml`
  - `python/tmpdir/rule-specialdir.yml` - It is perfectly fine to use `/dev/shm` as a tmpfs. Rule for using /tmp/ directly is flagged in `rule-hardcodedtmp.yml`
  - `python/tmpdir/rule-tempnam.yml` - `tempnam` was removed in Python 3, Python 2.7 is no longer supported
  - `python/urlopen/rule-urllib_urlopen2.yml` - Duplicate of `rule-urllib_urlopen1.yml` and also missing patterns
  - `python/xml/rule-import_pickle.yml` - Duplicate rule, see `deserialization/rule-pickle.yml`
  - `python/xml/rule-import_...` - Removed all `import` rules as they are just duplicates of the other rules

## v1.3.42

- `csharp/deserialization/rule-InsecureDeserialization.yml` - Convert to taint mode and improve precision to illiminate false-positive (!228)

## v1.3.41

- Remove poor Go rules (!216)
  - `go/audit/rule-unhandled_error.yml` - Empty placeholder rule
  - `go/blocklist/rule-blocklist-cgi.yml` - Only problematic in Go <1.6.3 and we can't currently determine the version
  - `go/crypto/rule-weakcrypto.yml` - Removed in favor of crypto blocklist rules with better descriptions and recommendations

## v1.3.40

- Remove poor or outdated C rules (!215)
  - c/buffer/rule-char_TCHAR.yml - Using character arrays is fine
  - c/buffer/rule-getchar_fgetc.yml - Using getchar does not constitute a vulnerability
  - c/buffer/rule-getopt_getopt_long.yml - This is a bug from 1999, see: https://stackoverflow.com/questions/64305167/flawfinder-error-internal-buffer-overflows-how-to-limit-string-input-size-and
  - c/misc/rule-chroot.yml - Does not point to any specific vulnerability.
  - c/misc/rule-InitializeCriticalSection.yml - This is no longer true since XP / 2003
  - c/race/rule-chgrp.yml - There is no such function (only a unix command line utility)
  - c/input/recv_recvfrom.yml - This is a source not a sink

## v1.3.39

- Revert rule changes made in (!193), (!198), (!199), (!197), (!194), and (!188) to allow for staged release of those MRs (!214)
- Fix `$ADDR` var bind error in `find_sec_bugs_scala.URLCONNECTION_SSRF_FD` scala rule (!214)
- Fix `$PWD` var bind error in `find_sec_bugs.HARD_CODE_PASSWORD` java rule (!214)

## v1.3.38

- Change rule ID format from `find_sec_bugs.XYZ` to `find_sec_bugs_scala.XYZ` for Scala rules (!202)

## v1.3.37

- Disable SAST `message` field wordwrap and update rules that had incorrectly wrapped URLs. (!200)

## v1.3.36

- Remove Java Rules (!193)
  - `java/cookie/rule-CookiePersistent.yml` - Cookies may not contain sensitive information and should be removed to be consistent with C# rules
  - `java/cookie/rule-CookieUsage.yml` - Cookies may not contain sensitive information and should be removed to be consistent with C# rules
  - `java/cookie/rule-RequestParamToCookie.yml` - Duplicate rule of `rule-HttpResponseSplitting.yml`
  - `java/cookie/rule-TrustBoundaryViolation.yml` - Unnecessary, prone to false positives
  - `java/cors/rule-PermissiveCORS.yml` - The impact of setting \* in a CORS response is minimal, since credentials will not be sent
  - `java/crypto/rule-DefaultHTTPClient.yml` - While Apache client is deprecated, the default client will connect to a TLS1.3 only server
  - `java/endpoint/rule-UnencryptedSocket.yml` - Using a non-TLS socket is perfectly acceptable in many circumstances
  - `java/endpoint/rule-InsecureServlet.yml` - It's perfectly acceptable to access the data from these methods. Additionally, there is no way a customer could 'fix' this
  - `java/endpoint/rule-JaxRsEndpoint.yml` - Incomplete rule, original [SpotBugs rule](https://find-sec-bugs.github.io/bugs.htm#JAXRS_ENDPOINT) is too broad and prone to false positives
  - `java/endpoint/rule-JaxWsEndpoint.yml` - Incomplete rule, original [SpotBugs rule](https://find-sec-bugs.github.io/bugs.htm#JAXWS_ENDPOINT) is too broad and prone to false positives
  - `java/file/rule-FileUploadFileName.yml` - This is a source not a sink
  - `java/form/rule-FormValidate.yml` - ActionForm/ValidatorForm is from Struts 1.1, which was EoL'd 2013
  - `java/inject/rule-AWSQueryInjection.yml` - SimpleDB, while still technically supported, is deprecated and no longer available to new accounts
  - `java/inject/rule-BeanPropertyInjection.yml` - Apache common collections 3 is no longer available and only works on Java 1.3
  - `java/inject/rule-CustomInjectionSQLString.yml` - Prone to false positives and rules do not necessarily match variables that will be used in a SQL query
  - `java/inject/rule-PathTraversalIn.yml` - Logic handled better by `rule-SpotbugsPathTraversalAbsolute.yml`
  - `java/inject/rule-PathTraversalOut.yml` - Logic handled better by `rule-SpotbugsPathTraversalAbsolute.yml`
  - `java/ldap/rule-EntryPoisoning.yml` - $SCOPE could legitimately have a value, logic handled better by `inject/rule-LDAPInjection`
  - `java/password/rule-HardcodeKeySuspiciousName.yml` - Secrets scanning should be used instead
  - `java/password/rule-HardcodeKeySuspiciousValue.yml` - Secrets scanning should be used instead
  - `java/perm/rule-OverlyPermissiveFilePermissionObj.yml` - Logic handled better by `java/perm/rule-OverlyPermissiveFilePermissionInline.yml`
  - `java/strings/rule-ImproperUnicode.yml` - Code quality issue more than a security issue
  - `java/unsafe/rule-InformationExposure.yml` - Printing stack trace information to the local machine is perfectly acceptable
  - `java/unsafe/rule-InformationExposureVariant2.yml` - Printing stack trace information to the local machine is perfectly acceptable
  - `java/xml/rule-ApacheXmlRpc.yml` - Apache Xml RPC was deprecated in 2013
  - `java/xss/rule-RequestWrapper.yml` - Appears to be a custom rule, `stripXSS()` is not a valid override
  - `java/xss/rule-XSSServlet.yml` - Duplicate of `java/xss/rule-XSSReqParamToServletWriter.yml`
  - `java/xss/rule-XSSServletParameter.yml` This is a source not a sink
  - `java/xxe/rule-XPathXXE.yml` - Rule matches a hardcoded variable name, and has no namespace/import associated with it. Better XXE rule required
  - `java/xxe/rule-Trans.yml` - Duplicate of `java/xml/rule-XsltTransform.yml` with less information

## v1.3.34

- Remove poor C# rules (!199)
  - `csharp/cache/rule-OutputCacheConflicts.yml` - Unable to confirm vulnerability
  - `csharp/other/rule-AuthorizationBypass.yml` - Highly prone to false positives as it assumes any controller without `[AllowAnonymous]` or `[Authorize]` is an authorization bypass

## v1.3.33

- Remove poor Python rules (!197)
  - `python/cgi/rule-import_httpoxy.yml` - Not vulnerable since 2016 https://bugs.python.org/issue27568
  - `python/crypto/rule-import_pyghmi.yml` - Old rule from 2013 https://www.cisa.gov/news-events/alerts/2013/07/26/risks-using-intelligent-platform-management-interface-ipmi
  - `python/escaping/rule-mark_safe.yml` - Duplicate of `rule-django.yml`
  - `python/exception/rule-try_except_continue.yml` - Not a security rule
  - `python/exception/rule-try_except_pass.yml` - Not a security rule
  - `python/ftp/rule-import_ftplib.yml` - Duplicate rule, see `rule-ftplib.yml`
  - `python/https/rule-httpsconnection.yml` - Software Composition Analysis (SCA) problem, not a SAST problem (flag if python < 3.4.3 and HTTPSConnection is used)
  - `python/secrets/` - enable secret detection instead
  - `python/telnet/rule-telnetlib.yml` - Duplicate of `rule-import_telnib.yml`
  - `python/tmpdir/rule-specialdir.yml` - It is perfectly fine to use `/dev/shm` as a tmpfs. Rule for using /tmp/ directly is flagged in `rule-hardcodedtmp.yml`
  - `python/tmpdir/rule-tempnam.yml` - `tempnam` was removed in Python 3, Python 2.7 is no longer supported
  - `python/urlopen/rule-urllib_urlopen2.yml` - Duplicate of `rule-urllib_urlopen1.yml` and also missing patterns
  - `python/xml/rule-import_pickle.yml` - Duplicate rule, see `deserialization/rule-pickle.yml`
  - `python/xml/rule-import_...` - Removed all `import` rules as they are just duplicates of the other rules

## v1.3.32

- Remove poor Go rules (!194)
  - `go/audit/rule-unhandled_error.yml` - Empty placeholder rule
  - `go/blocklist/rule-blocklist-cgi.yml` - Only problematic in Go <1.6.3 and we can't currently determine the version
  - `go/crypto/rule-weakcrypto.yml` - Removed in favor of crypto blocklist rules with better descriptions and recommendations

## v1.3.31

- Remove poor or outdated C rules (!188)
  - `c/buffer/rule-char_TCHAR.yml` - Using character arrays is fine
  - `c/buffer/rule-getchar_fgetc.yml` - Using getchar does not constitute a vulnerability
  - `c/buffer/rule-getopt_getopt_long.yml` - This is a bug from 1999, see: https://stackoverflow.com/questions/64305167/flawfinder-error-internal-buffer-overflows-how-to-limit-string-input-size-and
  - `c/misc/rule-chroot.yml` - Does not point to any specific vulnerability.
  - `c/misc/rule-InitializeCriticalSection.yml` - This is no longer true since XP / 2003
  - `c/race/rule-chgrp.yml` - There is no such function (only a unix command line utility)
  - `c/input/recv_recvfrom.yml` - This is a source not a sink

## v1.3.30

- Enhance Python ruleset descriptions and titles (!170)

## v1.3.29

- Improve Go memory aliasing in `G601` (!187)

## v1.3.28

- Enhance Javascript ruleset descriptions and titles (!166)

## v1.3.27

- Update Java `rule-SSRF.yml` to match more cases under `java.net.*` package (!186)
- Add Java rule `rule-WeakTLSProtocolVersion.yml` to detect weak TLS versions (!186)

## v1.3.26

- Update Javascript `rule-non_literal_fs_filename.yml` to only flag on fs modules (!183)

## v1.3.25

- Update Java `rule-SpotbugsPathTraversalAbsolute.yml` to handle getResourceAsStream and getResource (!182)

## v1.3.24

- Remove `-1` from all eslint rule IDs (!177)

## v1.3.23

- Update Java `rule-CommandInjection.yml` to match concatenated strings (!169)
- Update Java `rule-SpelView.yml` to also match `ExpressionParser` interface methods (!169)
- Update Java `rule-XpathInjection.yml` to match actual XPath import path (!169)

## v1.3.22

- Update Java `rule-CommandInjection.yml` with ability to match on String arrays (!168)

## v1.3.21

- Update Java `rule-BlowfishKeySize.yml` to add back missing `metavariable` (!169)
- Update Java rules with minor grammatical fixes (!169)

## v1.3.20

- Enhance Java ruleset descriptions and titles (!144)

## v1.3.19

- Update Primary identifiers for `bandit.B303` and `bandit.B304` so that they match the published rules in semgrep (!165)
- Remove `-1` from bandit ruleset IDs and primary identifiers to match the published rules in semgrep (!165)

## v1.3.18

- Update rules that were missing titles by moving them to shortDescription instead of cwe (!161)

## v1.3.17

- Update Primary identifiers for `bandit.B303` and `bandit.B304` so that they match the published rules in semgrep (!155)

## v1.3.16

- Update Primary identifiers for `bandit.B103` so that they match the published rules in semgrep (!154)

## v1.3.15

- Update primary identifier of `bandit.B108-2` to `bandit.B108-1` (!153)

## v1.3.14

- Find Sec Bugs singular rule IDs should include `-1` (!151)
- Security Code Scan singular rule IDs should include `-1` (!151)

## v1.3.13

- feat: Drop high-FP eslint detect-object-injection rule (!151)

## v1.3.12

- Gosec singular rule IDs should include `-1` (!149)

## v1.3.11

- Flawfinder singular rule IDs should include `-1` (!147)

## v1.3.10

- Fix typos in message of yaml load rule (!145)

## v1.3.9

- Enhance usecase coverage for Scala rules (!142)
- Remove redundant mapping of find_sec_bugs in Scala mapping (!142)
- Introduce `native_analyzer` property in the mappings file and use it for primary ID prefix (!142)

## v1.3.8

- Enhance Go ruleset descriptions and titles (!137)

## v1.3.7

- Revert primary identifier changes in !101 to align identifiers to previously-shipped rules (!138)

## v1.3.6

- Update pattern of avoid PyYAML.load in bandit.b506 (!140)

## v1.3.5

- Update C# SQL Injection with link for more details (!139)

## v1.3.4

- Add `generic_error_disclosure` rule for node.js (!124)

## v1.3.3

- Enhance C# ruleset descriptions and titles (!134)

## v1.3.2

- Update `metadata.owasp` to adhere to the pattern `A{number}:{year}-{Title}` (!136)

## v1.3.1

- Enhance C ruleset descriptions and titles (!128)
- Add shortDescription titles to C rulesets (!128)
- Add valdiation to confirm that either cwe tag contains title, or shortDescription is defined (!128)

## v1.3.0

- Add missing OWASP Top10 2017 Categories to C rulesets (!123)
- Fix java/scala OWASP Categories to include missing numerical identifier (!123)
- Add owasp metadata validation to schema (!123)

## v1.2.8

- Improve B608 to work with control flow (!126)

## v1.2.7

- Fix Bandit B113 positional arguments FPs (!122)

## v1.2.6

- Synchronize bandit upstream rules in the ruleset (!119)

## v1.2.5

- Synchronize new upstream rules in the ruleset (!112)

## v1.2.4

- Adjust bandit severity (!116)

## v1.2.3

- Rule refinements for Go (!115)

## v1.2.2

- Rule refinements for Scala (!113)

## v1.2.1

- Support Oracle, Postgres and MySql in .net (community contribution from @masakura) (!107)

## v1.2.0

- Scala support (!109)

## v1.1.12

- Cover more permutations for try...except.. cases (!106)

## v1.1.11

- Eliminate rules that use the `generic` feature (!105)

## v1.1.10

- Fix Bandit B101 rule coverage (!102)

## v1.1.9

- Remove extra colon in bandit rules (!98)

## v1.1.8

- Use single primary id (!101)

## v1.1.7

- Incorporating feedback to improve bandit rule-set (!88)

## v1.1.6

- C# rule refinement (community contribution from @masakura) (!100)

## v1.1.5

- Eliminate FPs for SQLi rule (!95)

## v1.1.4

- Eliminate FPs for SpotBugs hardcoded password rule (!96)

## v1.1.3

- Include eslint security prefix for secondary identifiers only (!95)

## v1.1.2

- Remove security prefix (!94)

## v1.1.1

- Moving the previous id representation; adding more meta information (!93)

## v1.1.0

- Changing deployment target to `/dist`, incorporate meta-information into
  generated rule-packs, update documentation (!87)
