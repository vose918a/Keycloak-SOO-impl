package org.keycloak.keycloaktest.service;

import lombok.AllArgsConstructor;
import org.keycloak.keycloaktest.document.Task;
import org.keycloak.keycloaktest.repository.TaskRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getTaskByOwner(String owner){
        return taskRepository.getTasksByOwnerId(UUID.fromString(owner));
    }

    public Optional<Task> getTaskById(String id){
        return taskRepository.findById(UUID.fromString(id));
    }

    public List<Task> getTaskByCreatedDate(String createdDate){
        return taskRepository.getTasksByCretedDate(Date.valueOf(createdDate));
    }

    public Task createNewTask(Task task){
        try {
            if(taskRepository.findById(task.getId()).isEmpty()) {
                return taskRepository.save(task);
            } else {return null;}
        }catch(Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public Task updateTask(Task newTask,String id){
        Task oldTask = setTask(getTaskById(id));
        oldTask.setStatus(newTask.getStatus());
        oldTask.setDescription(newTask.getDescription());
        try {
            if(getTaskById(id).isPresent()){
                return taskRepository.save(oldTask);
            }
        }catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
        return null;
    }

    public Boolean deleteTask(String id){
        try{
            if (getTaskById(id).isPresent()) {
                taskRepository.deleteById(UUID.fromString(id));
                return true;
            }
            return false;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Task setTask(Optional<Task> taskOptional){
        Task task = new Task();
        task.setId(taskOptional.get().getId());
        task.setDescription(taskOptional.get().getDescription());
        task.setCretedDate(taskOptional.get().getCretedDate());
        task.setOwnerId(taskOptional.get().getOwnerId());
        return task;
    }

    public UUID getSubFromJWT(Jwt jwt){
        String sub = jwt.getClaim("sub");
        return UUID.fromString(sub);
    }
}
