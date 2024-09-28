package com.todo.list_task_todo.file.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.todo.list_task_todo.file.dto.FileDto;
import com.todo.list_task_todo.file.dto.UploadResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemStorage implements Storage{


  @Override
  public UploadResponse upload(final FileDto fileDto, final InputStream content) {
    return null;
  }
}
