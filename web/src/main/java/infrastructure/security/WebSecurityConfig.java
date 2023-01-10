package infrastructure.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "infrastructure")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/add-car.html").hasRole("admin")
                .antMatchers("/delete-car.html").hasRole("admin")
                .antMatchers("/edit-car.html").hasRole("admin")
                .antMatchers("/create-order.html").hasAnyRole("admin", "user")
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index.html", true)
                .failureUrl("/sign-up.html")
                .and()
                .logout()
                .deleteCookies("JSESSIONID");
    }

    public void configureGlobalSecurity(AuthenticationManagerBuilder auth,
                                        AuthenticationService service) throws Exception {
        auth.userDetailsService(service);
    }
}
