package com.todo.list_task_todo.file.service;

import java.io.InputStream;

import com.todo.list_task_todo.file.dto.FileDto;
import com.todo.list_task_todo.file.dto.UploadFileDto;

public interface Storage {
  void upload(FileDto fileDto, InputStream content);

  InputStream download(FileDto fileDto);
  String fileLocation(UploadFileDto fileDto);
}
