package com.todo.list_task_todo.file.common;

import org.springframework.web.multipart.MultipartFile;

import com.todo.list_task_todo.file.dto.UploadFileDto;

public class FileHelper {
  public static UploadFileDto fromMultipartFile(final MultipartFile file) {
    return UploadFileDto.builder()
        .fileName(file.getOriginalFilename())
        .contentType(file.getContentType())
        .size(file.getSize())
        .inputStreamSupplier(file::getInputStream)
        .build();
  }
}
