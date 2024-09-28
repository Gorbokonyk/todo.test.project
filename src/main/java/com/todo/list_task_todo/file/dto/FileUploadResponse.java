package com.todo.list_task_todo.file.dto;

import lombok.Data;

@Data
public class FileUploadResponse {
  private long id;
  private String path;
  private String fileUuid;
  private String name;
  private String contentType;
  private long size;
}
