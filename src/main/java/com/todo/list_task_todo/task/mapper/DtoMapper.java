package com.todo.list_task_todo.task.mapper;

import org.mapstruct.Mapper;

import com.todo.list_task_todo.task.dto.TaskCreateRequest;
import com.todo.list_task_todo.task.dto.TaskCreateResponse;
import com.todo.list_task_todo.task.model.TaskEntity;

@Mapper(componentModel = "spring")
public interface DtoMapper {
  TaskEntity fromCreateRequest(TaskCreateRequest taskCreateRequest);
  TaskCreateResponse toCreateResponse(TaskEntity taskEntity);
}