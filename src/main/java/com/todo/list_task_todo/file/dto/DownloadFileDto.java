package com.todo.list_task_todo.file.dto;

import java.io.IOException;
import java.io.InputStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DownloadFileDto {
  private String fileName;
  private String contentType;
  private Long size;
  private InputStreamSupplier inputStreamSupplier;

  public InputStream getInputStream() throws IOException {
    return inputStreamSupplier.getInputStream();
  }

  public UploadFileDto toUploadFileDto() {
    return UploadFileDto.builder()
        .fileName(fileName)
        .contentType(contentType)
        .size(size)
        .inputStreamSupplier(inputStreamSupplier)
        .build();
  }
}