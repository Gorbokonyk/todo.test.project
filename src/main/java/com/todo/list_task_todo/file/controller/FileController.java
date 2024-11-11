package com.todo.list_task_todo.file.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list_task_todo.file.common.FileHelper;
import com.todo.list_task_todo.file.dto.FileUploadResponse;
import com.todo.list_task_todo.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FileController {

  private static final String GENERIC_FILES = "generic-files";

  private final FileService fileService;

  @PostMapping("/file")
  @ResponseStatus(code = HttpStatus.CREATED)
  public FileUploadResponse upload(@RequestParam("file") final MultipartFile file) {
    return fileService.upload(GENERIC_FILES, FileHelper.fromMultipartFile(file));
  }

  @PostMapping("/files")
  @ResponseStatus(code = HttpStatus.CREATED)
  public List<FileUploadResponse> uploadMultiple(@RequestParam("files") final MultipartFile[] files) {
    return Arrays.stream(files)
        .map(this::upload)
        .toList();
  }

//  @GetMapping("/files")
//  public FileListResponse list(final Pageable pageable, @RequestParam(required = false) final IncludeFilter deleted) {
//    return fileService.list(GENERIC_FILES, pageable, deleted);
//  }
}
