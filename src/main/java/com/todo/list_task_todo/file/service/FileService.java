package com.todo.list_task_todo.file.service;


import com.todo.list_task_todo.file.dto.FileUploadResponse;
import com.todo.list_task_todo.file.dto.UploadFileDto;

public interface FileService {
  FileUploadResponse upload(String path, UploadFileDto uploadFile);

}
