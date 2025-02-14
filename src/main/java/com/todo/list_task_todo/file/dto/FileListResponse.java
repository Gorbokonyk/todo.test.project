package com.todo.list_task_todo.file.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class FileListResponse {
  private List<FileEntry> data;

  @Data
  public static class FileEntry {
    private Long id;
    private String path;
    private String fileUuid;
    private String name;
    private String contentType;
    private long size;
  }
}
