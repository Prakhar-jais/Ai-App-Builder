package com.client.project.app_builder.dto.subscription;

public record PlanResponse(Long id,
        String name,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Boolean unlimitedAi,String price){

}
