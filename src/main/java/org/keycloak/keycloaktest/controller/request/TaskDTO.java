package org.keycloak.keycloaktest.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class TaskDTO {
    @NotBlank
    private String description;
    @NotBlank
    private Date cretedDate;
    @NotBlank
    private String status;
    @NotBlank
    private String ownerId;
}
