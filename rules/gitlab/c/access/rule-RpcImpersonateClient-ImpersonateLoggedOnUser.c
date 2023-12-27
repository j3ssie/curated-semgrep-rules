// License: MIT (c) GitLab Inc.

#include <windows.h>

int main(void) {

  HANDLE userToken, pipeHandle, threadToken;
  HWND hWnd;
  CtxtHandle securityContext;

  // ruleid: c_access_rule-RpcImpersonateClient-ImpersonateLoggedOnUser
  RpcImpersonateClient(NULL);

  // ruleid: c_access_rule-RpcImpersonateClient-ImpersonateLoggedOnUser
  ImpersonateLoggedOnUser(userToken);

  // ruleid: c_access_rule-RpcImpersonateClient-ImpersonateLoggedOnUser
  CoImpersonateClient();

  // ruleid: c_access_rule-RpcImpersonateClient-ImpersonateLoggedOnUser
  ImpersonateNamedPipeClient(pipeHandle);

  // ruleid: c_access_rule-RpcImpersonateClient-ImpersonateLoggedOnUser
  ImpersonateDdeClientWindow(hWnd);

  // ruleid: c_access_rule-RpcImpersonateClient-ImpersonateLoggedOnUser
  ImpersonateSecurityContext(&securityContext);

  // ruleid: c_access_rule-RpcImpersonateClient-ImpersonateLoggedOnUser
  SetThreadToken(NULL, threadToken);

  return 0;
}
