package com.todo.list_task_todo.task.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "todo_list_tasks")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class TaskEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ToString.Include
  private Long id;

  @Column(name = "title")
  @ToString.Include
  private String title;

  @Column(name = "completed")
  @ToString.Include
  private Boolean completed;
}
