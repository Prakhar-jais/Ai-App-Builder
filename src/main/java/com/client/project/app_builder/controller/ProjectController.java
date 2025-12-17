package com.client.project.app_builder.controller;

import com.client.project.app_builder.dto.project.ProjectRequest;
import com.client.project.app_builder.dto.project.ProjectResponse;
import com.client.project.app_builder.dto.project.ProjectSummaryResponse;
import com.client.project.app_builder.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)

public class ProjectController {

    ProjectService projectService;// all these fields must be private and final if you want lombok to make constructor injection else it would be null

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects(){
        // can only be called by someone who is currently logged in
        Long userId = 1L; // TODO : update later with real spring security

        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getUserProjectById(id,userId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request,userId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id,@RequestBody @Valid ProjectRequest request){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.updateProject(id,request,userId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        Long userId = 1L;
        projectService.softDelete(id,userId);
        return ResponseEntity.noContent().build();
    }

}
