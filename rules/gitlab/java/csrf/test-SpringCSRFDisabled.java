// License: LGPL-3.0 License (c) find-sec-bugs
package csrf;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;

@EnableWebSecurity
public class SpringCSRFDisabled extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // instance 1
        http.csrf().disable();

        // instance 2
        CsrfConfigurer<HttpSecurity> csrf = http.csrf();
        // ...
        csrf.disable();
    }
}
