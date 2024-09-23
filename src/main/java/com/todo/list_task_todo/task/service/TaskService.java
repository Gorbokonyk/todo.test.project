package com.todo.list_task_todo.task.service;

import org.springframework.stereotype.Service;

import com.todo.list_task_todo.task.dto.TaskCreateRequest;
import com.todo.list_task_todo.task.dto.TaskCreateResponse;
import com.todo.list_task_todo.task.mapper.DtoMapper;
import com.todo.list_task_todo.task.model.TaskEntity;
import com.todo.list_task_todo.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final DtoMapper mapper;

  public TaskCreateResponse create(final TaskCreateRequest taskCreateRequest) {
    final TaskEntity printerEntity = mapper.fromCreateRequest(taskCreateRequest);
    taskRepository.save(printerEntity);
    return mapper.toCreateResponse(printerEntity);
  }
}
