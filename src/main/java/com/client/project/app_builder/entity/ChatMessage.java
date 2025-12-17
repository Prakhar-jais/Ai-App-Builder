package com.client.project.app_builder.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.aspectj.bridge.Message;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    Long id;
    ChatSession chatSession;
    String content;
    MessageRole role;
    String toolCalls;
    Integer tokensUsed;
    Instant createdAt;
}
