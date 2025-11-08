package memoire.api.memoire_licence.security;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",                     // Nom du schéma de sécurité
        type = SecuritySchemeType.HTTP,          // Type HTTP
        scheme = "bearer",                       // Indique que c’est un Bearer token
        bearerFormat = "JWT",                    // Format du token
        in = SecuritySchemeIn.HEADER             // Le token sera envoyé dans le header
)
public class OpenApiConfig {
}
