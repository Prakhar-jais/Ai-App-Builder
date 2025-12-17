package com.client.project.app_builder.entity;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatSession {
    Project project;
    User user;
    String title;
    Instant createdAt;
    Instant deletedAt;
    Instant updatedAt;
}
