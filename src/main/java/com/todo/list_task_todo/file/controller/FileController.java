package com.todo.list_task_todo.file.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    return fileService.upload(FileHelper.fromMultipartFile(file));
  }

  @PostMapping("/files")
  @ResponseStatus(code = HttpStatus.CREATED)
  public List<FileUploadResponse> uploadMultiple(@RequestParam("files") final MultipartFile[] files) {
    return Arrays.stream(files)
        .map(this::upload)
        .toList();
  }

  @GetMapping("/files/{id}")
  public ResponseEntity<Resource> download(@PathVariable final long id) {
    return FileHelper.toResponseEntityAsAttachment(fileService.download(GENERIC_FILES, id));
  }

//  @GetMapping("/files")
//  public FileListResponse list(final Pageable pageable) {
//    ModelAndView map = new ModelAndView("index");
//    FileListResponse list = fileService.list(GENERIC_FILES, pageable);
//    map.addObject("lists", list);
//    return list;
//  }


//    @GetMapping("/files")
//  public ModelAndView  list(final Pageable pageable) {
//    ModelAndView map = new ModelAndView("fileUpload");
//    FileListResponse list = fileService.list(GENERIC_FILES, pageable);
//    map.addObject("lists", list.getData());
//    return map;
//  }

  @GetMapping("/msg")
  public String val() {
    return "Hello World";
  }
}
