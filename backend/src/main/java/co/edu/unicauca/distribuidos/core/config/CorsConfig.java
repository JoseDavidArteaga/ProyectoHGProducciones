package co.edu.unicauca.distribuidos.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        
        // Permitir orígenes específicos
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        
        // Permitir métodos HTTP específicos
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        
        // Permitir headers específicos
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        
        // Permitir credenciales
        corsConfiguration.setAllowCredentials(true);
        
        // Configurar las rutas que aplicarán CORS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        
        return new CorsFilter(source);
    }
}