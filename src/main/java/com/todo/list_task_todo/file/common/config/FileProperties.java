package com.todo.list_task_todo.file.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.file")
public class FileProperties {
  private String storage;
  private FileSystemProperties fileSystem;
  private AWSS3Properties awsS3;

  @Data
  public static class FileSystemProperties {
    private String uploadDir;
  }

  @Data
  public static class AWSS3Properties {
    private String bucket;
  }
}
