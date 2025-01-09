package com.todo.list_task_todo.task.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class TaskListResponse {
  private List<Task> data;

  @Data
  public static class Task {
    private Long id;
    private String title;
    private Boolean completed;
  }
}
