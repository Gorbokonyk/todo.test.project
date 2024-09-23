package com.todo.list_task_todo.task.cntroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list_task_todo.task.dto.TaskCreateRequest;
import com.todo.list_task_todo.task.dto.TaskCreateResponse;
import com.todo.list_task_todo.task.service.TaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public TaskCreateResponse create(@RequestBody final TaskCreateRequest taskCreateRequest) {
    return taskService.create(taskCreateRequest);
  }
}
