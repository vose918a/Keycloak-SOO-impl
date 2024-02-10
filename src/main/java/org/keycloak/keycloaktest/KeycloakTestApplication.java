package org.keycloak.keycloaktest;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})

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
