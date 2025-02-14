package com.todo.list_task_todo.file.service;


import org.springframework.data.domain.Pageable;

import com.todo.list_task_todo.file.dto.DownloadFileDto;
import com.todo.list_task_todo.file.dto.FileListResponse;
import com.todo.list_task_todo.file.dto.FileUploadResponse;
import com.todo.list_task_todo.file.dto.UploadFileDto;

public interface FileService {
  FileUploadResponse upload(UploadFileDto uploadFile);

  FileListResponse list(Pageable pageable);

  DownloadFileDto download(String path, long fileId);
}
