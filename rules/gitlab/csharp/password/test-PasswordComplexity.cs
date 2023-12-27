// License: LGPL-3.0 License (c) security-code-scan
using Microsoft.AspNet.Identity;
class PasswordLength {
    public void pw1() {
        PasswordValidator pwdv = new PasswordValidator
        {
            RequiredLength = 6,
        };
    }
    public void pw2() {
        PasswordValidator pwdv = new PasswordValidator
        {
            RequiredLength = 8,
            RequireNonLetterOrDigit = true,
            RequireDigit = true,
            RequireLowercase = true,
            RequireUppercase = true,
        };
    }
}
