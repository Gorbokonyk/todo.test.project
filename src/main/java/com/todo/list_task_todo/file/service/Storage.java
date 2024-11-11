package com.todo.list_task_todo.file.service;

import java.io.InputStream;

import com.todo.list_task_todo.file.dto.FileDto;

public interface Storage {
  void upload(FileDto fileDto, InputStream content);
}
