package com.todo.list_task_todo.file.common;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list_task_todo.file.dto.DownloadFileDto;
import com.todo.list_task_todo.file.dto.UploadFileDto;
import lombok.SneakyThrows;

public class FileHelper {
  public static UploadFileDto fromMultipartFile(final MultipartFile file) {
    return UploadFileDto.builder()
        .fileName(file.getOriginalFilename())
        .contentType(file.getContentType())
        .size(file.getSize())
        .inputStreamSupplier(file::getInputStream)
        .build();
  }

  public static ResponseEntity<Resource> toResponseEntityAsAttachment(final DownloadFileDto downloadFile) {
    final String fileName = downloadFile.getFileName();
    final ContentDisposition contentDisposition = ContentDisposition.attachment().filename(fileName).build();
    return toResponseEntity(downloadFile, contentDisposition);
  }

  @SneakyThrows(IOException.class)
  private static ResponseEntity<Resource> toResponseEntity(
      final DownloadFileDto downloadFile, final ContentDisposition contentDisposition) {

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(
        MediaTypeFactory.getMediaType(downloadFile.getFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM)
    );
    headers.setContentDisposition(contentDisposition);
    headers.setContentLength(downloadFile.getSize());

    final Resource resource = new InputStreamResource(downloadFile.getInputStream());
    return ResponseEntity.ok().headers(headers).body(resource);
  }
}
