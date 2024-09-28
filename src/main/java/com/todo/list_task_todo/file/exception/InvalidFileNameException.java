package com.todo.list_task_todo.file.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid file name")
public class InvalidFileNameException extends RuntimeException{
  public InvalidFileNameException(final String message) {
    super(message);
  }
}
