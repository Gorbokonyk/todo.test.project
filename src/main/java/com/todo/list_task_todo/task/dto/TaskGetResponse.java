package com.todo.list_task_todo.task.dto;

import lombok.Data;

@Data
public class TaskGetResponse {
  private Long id;
  private String title;
  private Boolean completed;
}
