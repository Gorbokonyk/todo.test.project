package com.todo.list_task_todo.file.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app.file.upload")
public class FileUploadProperties {
  private DataSize maxSize;
  private String defaultUploadDir;
}
