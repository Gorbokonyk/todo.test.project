package com.todo.list_task_todo.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;

import com.todo.list_task_todo.file.dto.FileDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class FileSystemStorage implements Storage {
  private final Path filesLocation;

  @SneakyThrows(IOException.class)
  public FileSystemStorage(FileUploadProperties fileUploadProperties) {
    this.filesLocation = Paths.get(fileUploadProperties.getDefaultUploadDisk());
    Files.createDirectories(this.filesLocation);
  }


  @Override
  @SneakyThrows(IOException.class)
  public void upload(final FileDto fileDto, final InputStream content) {
    final Path targetLocation = this.filesLocation.resolve(
            Paths.get(fileDto.getName()))
        .normalize().toAbsolutePath();
    Files.copy(content, targetLocation, StandardCopyOption.REPLACE_EXISTING);
  }
}
