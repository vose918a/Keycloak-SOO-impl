package org.keycloak.keycloaktest.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class Task {
    @Id
    private UUID id;
    private String description;
    private Date cretedDate;
    private Statues status;
    private UUID ownerId;
}
