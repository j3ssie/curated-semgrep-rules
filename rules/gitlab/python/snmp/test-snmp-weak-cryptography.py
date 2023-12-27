# License: MIT (c) GitLab Inc.
# source: https://bandit.readthedocs.io/en/latest/_modules/bandit/plugins/snmp_security_check.html#snmp_crypto_check

from pysnmp.hlapi import CommunityData, UsmUserData

# SHOULD FAIL
insecure = UsmUserData("securityName")
# SHOULD FAIL
auth_no_priv = UsmUserData("securityName","authName")
# SHOULD PASS
less_insecure = UsmUserData("securityName","authName","privName")
