package com.todo.list_task_todo.task.dto;

import lombok.Data;

@Data
public class TaskCreateResponse {
  private Long id;
  private String task;
  private Boolean completed;
}
