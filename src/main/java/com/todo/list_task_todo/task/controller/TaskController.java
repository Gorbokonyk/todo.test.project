package com.todo.list_task_todo.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list_task_todo.task.dto.TaskCreateRequest;
import com.todo.list_task_todo.task.dto.TaskCreateResponse;
import com.todo.list_task_todo.task.dto.TaskListResponse;
import com.todo.list_task_todo.task.service.TaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping
  public TaskListResponse list() {
    return taskService.list();
  }

  @GetMapping("completed")
  public TaskListResponse listCompleted() {
    return taskService.listCompleted();
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public TaskCreateResponse create(@RequestBody final TaskCreateRequest taskCreateRequest) {
    return taskService.create(taskCreateRequest);
  }

//
//  @PatchMapping("/{id}")
//  public TaskPatchResponse patchCompleted(@PathVariable final long id) {
//    return taskService.patchCompleted(id);
//  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final long id) {
    taskService.delete(id);
  }
}


