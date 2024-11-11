package com.todo.list_task_todo.file.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app.file.upload")
public class FileUploadProperties {
  private String defaultUploadDisk;
}
