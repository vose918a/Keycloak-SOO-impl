package org.keycloak.keycloaktest;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "Keycloak",
                openIdConnectUrl = "http://localhost:8081/realms/OAuth2-demo-realm/.well-known/openid-configuration/",
                scheme = "bearer",
                type = SecuritySchemeType.OPENIDCONNECT,
                in = SecuritySchemeIn.HEADER)
public class KeycloakTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeycloakTestApplication.class, args);
    }

}
