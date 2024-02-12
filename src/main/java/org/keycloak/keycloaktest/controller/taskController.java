package org.keycloak.keycloaktest.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.keycloak.keycloaktest.controller.request.TaskDTO;
import org.keycloak.keycloaktest.document.Statues;
import org.keycloak.keycloaktest.document.Task;
import org.keycloak.keycloaktest.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@SecurityRequirement(name = "Keycloak")
@AllArgsConstructor
public class taskController {

    private final TaskService taskService;

    @GetMapping("/test")
    @PreAuthorize("hasRole('user_client_role') || hasRole('admin_client_role')")
    public String getMethod() {
        return "Says hello";
    }

    @PostMapping("/create") @PreAuthorize("hasRole('user_client_role')")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO, @AuthenticationPrincipal Jwt jwt) {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setDescription(taskDTO.getDescription());
        task.setCretedDate(Date.valueOf(LocalDate.now()));
        task.setOwnerId(taskService.getSubFromJWT(jwt));
        task.setStatus(Statues.PENDING);
        return ResponseEntity.ok(taskService.createNewTask(task));
    }

    @GetMapping("/getTasks") @PreAuthorize("hasRole('user_client_role')")
    public ResponseEntity<List<Task>> getTasks(@AuthenticationPrincipal Jwt jwt){
        UUID ownerId = taskService.getSubFromJWT(jwt);
        return ResponseEntity.ok(taskService.getTaskByOwner(String.valueOf(ownerId)));
    }

    @PutMapping("/update/{taskId}") @PreAuthorize("hasRole('user_client_role')")
    public ResponseEntity<Task> updateTask(@PathVariable String taskId,@RequestBody TaskDTO taskDTO){
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Statues.valueOf(taskDTO.getStatus()));
        return ResponseEntity.ok(taskService.updateTask(task,taskId));
    }

    @DeleteMapping("/delete/{taskId}") @PreAuthorize("hasRole('user_client_role')")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId){
        return taskService.deleteTask(taskId) ?
                ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
