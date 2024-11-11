package com.todo.list_task_todo.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.todo.list_task_todo.file.dto.FileDto;
import com.todo.list_task_todo.file.dto.FileUploadResponse;
import com.todo.list_task_todo.file.dto.UploadFileDto;
import com.todo.list_task_todo.file.exception.InvalidFileNameException;
import com.todo.list_task_todo.file.mapper.FileMapper;
import com.todo.list_task_todo.file.model.FileEntity;
import com.todo.list_task_todo.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

  private final FileRepository fileRepository;
  private final FileMapper fileMapper;
  private final Storage storage;

  @Override
  @SneakyThrows(IOException.class)
  public FileUploadResponse upload(final String path, final UploadFileDto uploadFile) {
    validateFileName(uploadFile.getFileName());
    FileEntity fileEntity = FileEntity.builder()
        .path(path)
        .fileUuid(generateFileUuid())
        .name(uploadFile.getFileName())
        .contentType(uploadFile.getContentType())
        .size(uploadFile.getSize())
        .build();

    final FileDto fileDto = fileMapper.toFileDto(fileEntity);
    final InputStream inputStream = uploadFile.getInputStream();
    storage.upload(fileDto, inputStream);
    fileEntity = fileRepository.save(fileEntity);
    return fileMapper.toStoreFileResponse(fileEntity);
  }

  private void validateFileName(final String fileName) {
    if (fileName == null) {
      throw new InvalidFileNameException("Missing file name");
    } else if (!StringUtils.hasText(fileName)) {
      throw new InvalidFileNameException("Empty file name");
    }
  }


  private String generateFileUuid() {
    return UUID.randomUUID().toString();
  }
}
