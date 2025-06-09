package ar.com.up.theater.manager.configuration;


import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String base64Secret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // no necesitamos sesiones en un Resource Server
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/theater/hall/**").permitAll()
                        .requestMatchers("/theater/artists/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/theater/show").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder()))
                );
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // decodifica el secreto Base64 a bytes
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        SecretKey secretKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
        // construye el decoder de Nimbus
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}
