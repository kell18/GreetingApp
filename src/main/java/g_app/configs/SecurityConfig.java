package g_app.configs;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;*/

// @EnableWebSecurity
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/static*//**", "/index").permitAll()
                .antMatchers("/sign-in").hasRole("USER") // todo: check for some test page
                .and()
                .formLogin()
                .loginPage("/sign-up").failureUrl("/login-error");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // auth.jdbcAuthentication(). // todo
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/
}