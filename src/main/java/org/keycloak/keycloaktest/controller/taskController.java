package org.keycloak.keycloaktest.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@SecurityRequirement(name = "Keycloak")
public class taskController {

    @GetMapping("/test")
    @PreAuthorize("hasRole('user_client_role') || hasRole('admin_client_role')")
    public String getMethod() {
        return "Says hello";
    }
}
