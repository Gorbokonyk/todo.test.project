package com.todo.list_task_todo.task.dto;

import lombok.Data;

@Data
public class TaskCreateRequest {
  private String task;
  private boolean completed;
}