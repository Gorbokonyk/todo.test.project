package com.todo.list_task_todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.todo.list_task_todo.file.common.config.FileProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileProperties.class)
public class ListTaskToDoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ListTaskToDoApplication.class, args);
  }
}
