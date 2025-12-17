package com.client.project.app_builder.service;

import com.client.project.app_builder.dto.project.FileContentResponse;
import com.client.project.app_builder.dto.project.FileNode;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FileService {
      List<FileNode> getFileTree(Long projectId, Long userId) ;

      FileContentResponse getFileContent(Long projectId,String path, Long userId);
}
