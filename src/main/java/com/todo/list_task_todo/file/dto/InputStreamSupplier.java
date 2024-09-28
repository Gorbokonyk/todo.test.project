package com.todo.list_task_todo.file.dto;

import java.io.IOException;
import java.io.InputStream;

@FunctionalInterface
public interface InputStreamSupplier {
  InputStream getInputStream() throws IOException;
}
