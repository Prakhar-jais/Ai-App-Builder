package com.client.project.app_builder.controller;

import com.client.project.app_builder.dto.project.ProjectRequest;
import com.client.project.app_builder.dto.project.ProjectResponse;
import com.client.project.app_builder.dto.project.ProjectSummaryResponse;
import com.client.project.app_builder.security.AuthUtil;
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

    private final ProjectService projectService;// all these fields must be private and final if you want lombok to make constructor injection else it would be null
    private final AuthUtil authUtil;


    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects(){
        // can only be called by someone who is currently logged in

//        // TODO : update later with real spring security

        return ResponseEntity.ok(projectService.getUserProjects());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){

        return ResponseEntity.ok(projectService.getUserProjectById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id,@RequestBody @Valid ProjectRequest request){
        return ResponseEntity.ok(projectService.updateProject(id,request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){

        projectService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

}
