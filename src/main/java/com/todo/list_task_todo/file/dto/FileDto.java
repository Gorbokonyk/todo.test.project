package com.todo.list_task_todo.file.dto;

import lombok.Data;

@Data
public class FileDto {
  private String path;
  private String fileUuid;
  private String contentType;
  private String name;
  private long size;
}
