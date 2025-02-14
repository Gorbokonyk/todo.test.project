package com.todo.list_task_todo.file.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.todo.list_task_todo.file.dto.DownloadFileDto;
import com.todo.list_task_todo.file.dto.FileDto;
import com.todo.list_task_todo.file.dto.FileListResponse;
import com.todo.list_task_todo.file.dto.FileUploadResponse;
import com.todo.list_task_todo.file.dto.UploadFileDto;
import com.todo.list_task_todo.file.exception.InvalidFileNameException;
import com.todo.list_task_todo.file.mapper.FileMapper;
import com.todo.list_task_todo.file.model.FileEntity;
import com.todo.list_task_todo.file.repository.FileRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

  private final FileRepository fileRepository;
  private final FileSystemStorage fileSystemStorage;
  private final FileMapper fileMapper;
  private final Storage storage;


  @Override
  public FileListResponse list(Pageable pageable) {

    Specification<FileEntity> spec = null;
    List<FileEntity> all = fileRepository.findAll();

    final Page<FileEntity> page = fileRepository.findAll(spec, pageable);

    List<FileListResponse.FileEntry> list1 = all.stream().map(fileMapper::toListFilesResponseFileEntry).toList();
    final List<FileListResponse.FileEntry> list = page.getContent().stream()
        .map(fileMapper::toListFilesResponseFileEntry) // Assuming deleted filter is removed
        .toList();

    return FileListResponse.builder()
        .data(list1)
        .build();
  }

  @Override
  @SneakyThrows(IOException.class)
  public FileUploadResponse upload(final UploadFileDto uploadFile) {
    validateFileName(uploadFile.getFileName());
    FileEntity fileEntity = FileEntity.builder()
        .path(fileSystemStorage.fileLocation(uploadFile))
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

  @Override
  public DownloadFileDto download(final String path, final long id) {
    return download(getRaw(path, id));
  }

  @SneakyThrows(FileNotFoundException.class)
  private FileEntity getRaw(final String path, final long id) {
    Specification<FileEntity> spec = (root, query, criteriaBuilder) -> {
      Predicate pathPredicate = criteriaBuilder.equal(root.get("path"), path);
      Predicate idPredicate = criteriaBuilder.equal(root.get("path"), id);
      return criteriaBuilder.and(pathPredicate, idPredicate);
    };
    return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
  }

  private DownloadFileDto download(final FileEntity fileEntity) {
    return DownloadFileDto.builder()
        .fileName(fileEntity.getName())
        .contentType(fileEntity.getContentType())
        .size(fileEntity.getSize())
        .inputStreamSupplier(() -> this.storage.download(fileMapper.toFileDto(fileEntity)))
        .build();
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
