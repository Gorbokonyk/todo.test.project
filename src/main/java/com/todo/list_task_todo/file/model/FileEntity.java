package com.todo.list_task_todo.file.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "todo_tasks_file")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ToString.Include
  private Long id;

  @Column(name = "path")
  @ToString.Include
  private String path;

  @Column(name = "file_uuid")
  @ToString.Include
  private String fileUuid;

  @Column(name = "name")
  @ToString.Include
  private String name;

  @Column(name = "content_type")
  @ToString.Include
  private String contentType;

  @Column(name = "size")
  @ToString.Include
  private long size;

}
