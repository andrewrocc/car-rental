package infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "infrastructure")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests(
                authConfig -> {
                    authConfig.antMatchers("/").permitAll()
                            .antMatchers("/index.html").permitAll()
                            .antMatchers("/login.html").permitAll()
                            .antMatchers("/sign-up.html").permitAll()
                            .antMatchers("/car-table.html").permitAll()
                            .antMatchers("/car-info.html").permitAll()
                            .antMatchers("/search.html").permitAll()
                            .antMatchers("/photo.jpg").permitAll()
                            .antMatchers("/image/**").permitAll()
                            .antMatchers("/img/**").permitAll()
                            .antMatchers("/js/**").permitAll()
                            .antMatchers("/style/**").permitAll()
                            .anyRequest().authenticated();
        })
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", false)
                .failureUrl("/sign-up.html")
                .and()
                .logout()
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    public void configureGlobalSecurity(AuthenticationManagerBuilder auth,
                                        AuthenticationService service) throws Exception {
        auth.userDetailsService(service);
    }
}