package br.com.app.app_control.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.app.app_control.application.service.usuario.IUsuarioService;
import br.com.app.app_control.infrastructure.security.JwtTokenProvider;
import br.com.project.core.entities.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Set;

/**
 * Filter that processes JWT authentication for incoming HTTP requests.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final IUsuarioService usuarioService;

    /**
     * Constructor for JwtAuthenticationFilter.
     *
     * @param jwtTokenProvider The JWT token provider.
     * @param usuarioService The user service implementation.
     */
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, IUsuarioService usuarioService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioService = usuarioService;
    }

    /**
     * Filter method that is executed once per request.
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain.
     * @throws ServletException if an error occurs during filtering.
     * @throws IOException if an I/O error occurs during filtering.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getEmail(token);

            try {
                Usuario usuario = usuarioService.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

                Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(usuario.getRole()));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("User '{}' authenticated", email);
            } catch (RuntimeException e) {
                logger.error("Authentication error: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Falha na autenticação.");
                return;
            }
        } else {
            logger.warn("No valid JWT token found for request: {}", request.getRequestURI());
        }
        
        filterChain.doFilter(request, response);
    }
}
