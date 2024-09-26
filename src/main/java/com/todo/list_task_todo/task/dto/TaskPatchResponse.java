package com.todo.list_task_todo.task.dto;

import lombok.Data;

@Data
public class TaskPatchResponse {
  private Long id;
  private String task;
  private Boolean completed;
}