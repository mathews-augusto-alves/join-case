package br.com.app.app_control.infrastructure.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import br.com.app.app_control.application.service.usuario.IUsuarioService;
import br.com.app.app_control.infrastructure.security.JwtTokenProvider;

/**
 * Security configuration class that sets up security filters, CORS
 * configurations,
 * and exception handling for the application.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final IUsuarioService usuarioService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /**
     * Constructor for SecurityConfig.
     *
     * @param jwtTokenProvider               The JWT token provider.
     * @param usersService                   The user service implementation.
     * @param customAuthenticationEntryPoint The custom authentication entry point.
     */
    public SecurityConfig(
            final JwtTokenProvider jwtTokenProvider,
            final IUsuarioService usuarioService,
            final CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioService = usuarioService;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    /**
     * Configures the security filter chain.
     *
     * @param http         The HttpSecurity object.
     * @param introspector The HandlerMappingIntrospector object.
     * @return The configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
            throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll()
                        .requestMatchers("/public/swagger-ui.html", "/public/swagger-ui/**", "/public/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint))
                .headers(headers -> headers
                        .frameOptions().sameOrigin())
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, usuarioService), UsernamePasswordAuthenticationFilter.class);
        ;

        return http.build();
    }

    /**
     * Configures web security customizer to ignore certain requests.
     *
     * @return The configured WebSecurityCustomizer.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**");
    }

    /**
     * Configures CORS settings for the application.
     *
     * @return The configured UrlBasedCorsConfigurationSource.
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:3000/", "http://host.docker.internal:3000/", "http://172.17.0.1:3000/", "https://silver-space-fortnight-65v4v65p7vrf5xx4-3000.app.github.dev/"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedMethod("OPTIONS");
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
