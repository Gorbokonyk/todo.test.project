package com.todo.list_task_todo.file.dto;

import lombok.Data;

@Data
public class FilePatchResponse {
  private Long id;
  private String task;
  private Boolean completed;
}