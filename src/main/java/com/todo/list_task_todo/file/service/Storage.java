package com.todo.list_task_todo.file.service;

import java.io.InputStream;

import com.todo.list_task_todo.file.dto.FileDto;
import com.todo.list_task_todo.file.dto.UploadResponse;

public interface Storage {
  UploadResponse upload(FileDto fileDto, InputStream content);
}
