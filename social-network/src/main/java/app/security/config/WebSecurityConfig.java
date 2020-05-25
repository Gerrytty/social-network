package app.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan(basePackages = {"app"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final PersistentTokenRepository persistentTokenRepository;

    public WebSecurityConfig(@Qualifier(value = "customUserDetailsService") UserDetailsService userDetailsService,
                             PasswordEncoder passwordEncoder,
                             PersistentTokenRepository persistentTokenRepository) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.persistentTokenRepository = persistentTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/reg", "/auth").anonymous()
                .antMatchers("/users", "/profile", "/addPost", "/chat").authenticated()
                .antMatchers("/static/**").permitAll()

//                .and().csrf().disable()
                .and()

                .formLogin()
                .loginPage("/auth")
                .loginProcessingUrl("/auth/process")
                .usernameParameter("email")
                .failureUrl("/auth?error=true")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/profile")
                .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository);

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/auth")
                .deleteCookies("JSESSIONID", "remember-me")
                .clearAuthentication(true)
                .invalidateHttpSession(true);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}