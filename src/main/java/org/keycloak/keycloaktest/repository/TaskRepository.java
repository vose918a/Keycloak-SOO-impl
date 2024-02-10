package org.keycloak.keycloaktest.repository;

import org.keycloak.keycloaktest.document.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends MongoRepository<Task, UUID> {
    List<Task> getTasksByCretedDate(Date cretedDate);
    List<Task> getTasksByOwnerId(UUID ownerId);
}
