package com.todo.list_task_todo.file.dto;

import lombok.Data;

@Data
public class FileDto {
  private String path;
  private String fileUuid;
  private String contentType;
  private String name;
  private long size;

  public String buildFullPath() {
    if (path != null) {
      String fullPath = path;
      while (fullPath.startsWith("/")) {
        fullPath = fullPath.substring(1);
      }
      return fullPath.endsWith("/") ? fullPath + name : fullPath + "/" + name;
    } else {
      return name;
    }
  }
}
