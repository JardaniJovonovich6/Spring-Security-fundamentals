package production.ready.learn.springdev.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import production.ready.learn.springdev.filter.JwtAuthFilter;
import production.ready.learn.springdev.handler.OAuth2SuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/**" , "/posts" , "/home.html").permitAll()
//                        .requestMatchers("/posts/**").hasAnyRole("ADMIN")

                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2config ->
                        oauth2config.failureUrl("/login?error=true")
                                .successHandler(oAuth2SuccessHandler)
                );
//                .formLogin(Customizer.withDefaults();

        return httpSecurity.build();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }


//    @Bean
//    UserDetailsService InMemoryUserDetailsService(){
//
//        UserDetails normalUser = User
//                .withUsername("User1")
//                .password(passwordEncoder().encode("passuser1"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser = User
//                .withUsername("Admin1")
//                .password(passwordEncoder().encode("passadmin1"))
//                .roles("ADMIN")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(normalUser , adminUser);
//
//    }



}
