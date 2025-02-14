package com.todo.list_task_todo.file.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.todo.list_task_todo.file.common.config.FileUploadProperties;
import com.todo.list_task_todo.file.dto.FileDto;
import com.todo.list_task_todo.file.dto.UploadFileDto;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Getter
@Service
@Slf4j
@ConditionalOnProperty(name = "app.file.storage", havingValue = "file-system")
public class FileSystemStorage implements Storage {
  private final Path filesLocation;

  @SneakyThrows(IOException.class)
  public FileSystemStorage(FileUploadProperties fileUploadProperties) {
    this.filesLocation = Paths.get(fileUploadProperties.getDefaultUploadDir()).resolve("generic-files");
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

  @Override
  @SneakyThrows(FileNotFoundException.class)
  public InputStream download(final FileDto fileDto) {
    final Path filePath = this.filesLocation.resolve(fileDto.getName()).normalize();
    try {
      return new FileInputStream(filePath.toFile());
    } catch (final FileNotFoundException e) {
      throw new FileNotFoundException("File not found on file system: " + filePath);
    }
  }

  @Override
  public String fileLocation(final UploadFileDto fileDto) {
    final Path targetLocation = this.filesLocation.resolve(
            Paths.get(fileDto.getFileName()))
        .normalize().toAbsolutePath();
    return targetLocation.toString();
  }
}
