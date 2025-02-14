package com.todo.list_task_todo.file.mapper;

import org.mapstruct.Mapper;

import com.todo.list_task_todo.file.dto.FileDto;
import com.todo.list_task_todo.file.dto.FileListResponse;
import com.todo.list_task_todo.file.dto.FileUploadResponse;
import com.todo.list_task_todo.file.model.FileEntity;

@Mapper(componentModel = "spring")
public interface FileMapper {
  FileDto toFileDto(FileEntity fileEntity);
  FileUploadResponse toStoreFileResponse(FileEntity fileEntity);
  FileListResponse.FileEntry toListFilesResponseFileEntry(FileEntity fileEntity);
}
