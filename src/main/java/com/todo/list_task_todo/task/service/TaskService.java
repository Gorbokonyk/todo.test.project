package com.todo.list_task_todo.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.list_task_todo.task.dto.TaskCreateRequest;
import com.todo.list_task_todo.task.dto.TaskCreateResponse;
import com.todo.list_task_todo.task.dto.TaskListResponse;
import com.todo.list_task_todo.task.dto.TaskPatchResponse;
import com.todo.list_task_todo.task.exception.TaskNotFoundException;
import com.todo.list_task_todo.task.mapper.DtoMapper;
import com.todo.list_task_todo.task.model.TaskEntity;
import com.todo.list_task_todo.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final DtoMapper mapper;

  public TaskListResponse list() {
    final List<TaskEntity> all = taskRepository.findAll();
    final List<TaskListResponse.Task> list = all.stream().map(mapper::toListResponseItem).toList();
    System.out.println("Tasks: " + list);
    return TaskListResponse.builder().data(list).build();
  }

  public TaskListResponse listCompleted() {
    final List<TaskListResponse.Task> completedTasks = taskRepository.findAll().stream()
        .filter(TaskEntity::getCompleted)
        .map(mapper::toListResponseItem)// Filter only completed tasks
        .toList();
    return TaskListResponse.builder().data(completedTasks).build();
  }

  public TaskCreateResponse create(final TaskCreateRequest taskCreateRequest) {
    final TaskEntity printerEntity = mapper.fromCreateRequest(taskCreateRequest);
    taskRepository.save(printerEntity);
    return mapper.toCreateResponse(printerEntity);
  }

  public void createTask(final TaskCreateRequest taskCreateRequest) {
    final TaskEntity printerEntity = mapper.fromCreateRequest(taskCreateRequest);
    taskRepository.save(printerEntity);
  }

  public TaskPatchResponse patchCompleted(final long id) {
    TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    taskEntity.setCompleted(true);
    taskRepository.save(taskEntity);
    return mapper.toPatchResponse(taskEntity);
  }

  public void delete(final long id) {
    TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    taskRepository.delete(taskEntity);
  }
}
