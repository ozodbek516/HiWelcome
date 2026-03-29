package uz.hiwelcome.hiwelcome.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import uz.hiwelcome.hiwelcome.config.handler.JwtAuthenticationEntryPoint;
import uz.hiwelcome.hiwelcome.config.handler.MyAccessDeniedHandler;
import uz.hiwelcome.hiwelcome.config.jwt.JwtAuthenticationFilter;


@Configuration("authSecurityConfig")
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final MyAccessDeniedHandler myAccessDeniedHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          MyAccessDeniedHandler myAccessDeniedHandler,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          @Qualifier("corsConfigurationSource") CorsConfigurationSource corsConfigurationSource) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.myAccessDeniedHandler = myAccessDeniedHandler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    private static final String[] WHITE_LIST = {
            "/api/v1/auth/**", 
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/favicon.ico"
    };

    @Bean("authSecurityFilterChain")
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers("/api/hotels/**").hasRole("ADMIN")
                        .requestMatchers("/api/bookings/**").hasRole("USER")
                        .anyRequest().authenticated()
                )

                .exceptionHandling(handling -> {
                    handling.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                    handling.accessDeniedHandler(myAccessDeniedHandler);
                })

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
