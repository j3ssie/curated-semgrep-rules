# License: MIT (c) GitLab Inc.
# source: https://bandit.readthedocs.io/en/latest/_modules/bandit/plugins/snmp_security_check.html#snmp_insecure_version_check

from pysnmp.hlapi import CommunityData

# SHOULD FAIL
a = CommunityData('public', mpModel=0)
b = CommunityData('public', mpModel=1)
