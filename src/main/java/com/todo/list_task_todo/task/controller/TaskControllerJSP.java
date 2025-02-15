package com.todo.list_task_todo.task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todo.list_task_todo.file.common.FileHelper;
import com.todo.list_task_todo.file.dto.FileListResponse;
import com.todo.list_task_todo.file.service.FileService;
import com.todo.list_task_todo.task.dto.TaskCreateRequest;
import com.todo.list_task_todo.task.dto.TaskListResponse;
import com.todo.list_task_todo.task.dto.TaskPatchRequest;
import com.todo.list_task_todo.task.dto.TaskUpdateRequest;
import com.todo.list_task_todo.task.service.TaskService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v2/task")
@RequiredArgsConstructor
public class TaskControllerJSP {
  private final TaskService taskService;
  private final FileService fileService;

  private static final String GENERIC_FILES = "generic-files";

  @GetMapping()
  public String list(Model model, @ModelAttribute("message") String message) {
    TaskListResponse list = taskService.list();
    model.addAttribute("tasks", list.getData());
    model.addAttribute("message", message);
    return "ViewToDoList";
  }

  @GetMapping("/completed")
  public TaskListResponse listCompleted() {
    return taskService.listCompleted();
  }

  @GetMapping("/addToDoItem")
  @ResponseStatus(code = HttpStatus.CREATED)
  public String add(Model model) {
    model.addAttribute("task", new TaskCreateRequest());
    return "AddToDoItem";
  }

  @PostMapping("/create")
//  @ResponseStatus(code = HttpStatus.CREATED)
  public String create(@ModelAttribute("task") final TaskCreateRequest taskCreateRequest,
                       RedirectAttributes redirectAttributes) {
    taskService.createTask(taskCreateRequest);
    redirectAttributes.addAttribute("message", "Save Success");
    return "redirect:/api/v2/task";
  }

  @GetMapping("/editToDoItem/{id}")
  public String edit(@PathVariable long id, Model model) {
    model.addAttribute("task", taskService.get(id));
    return "EditToDoItem";
  }

  @PostMapping("/edit/{taskId}")
  public String update(@PathVariable final long taskId, final TaskUpdateRequest taskUpdateRequest,
                       RedirectAttributes redirectAttributes) {
    taskService.update(taskId, taskUpdateRequest);
    redirectAttributes.addFlashAttribute("message", "Edit Success");
    return "redirect:/api/v2/task";
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Map<String, String>> patchCompleted(@PathVariable long id, @RequestBody TaskPatchRequest taskPatchRequest) {
    Map<String, String> response = new HashMap<>();
      taskService.patchCompleted(id, taskPatchRequest);
      response.put("message", taskPatchRequest.isCompleted() ? "Task Completed" : "Task Not Completed");
      return ResponseEntity.ok(response);

  }

  @GetMapping("/deleteToDoItem/{id}")
  public String delete(@PathVariable final long id, RedirectAttributes redirectAttributes) {
    if(taskService.delete(id)){
      redirectAttributes.addFlashAttribute("message", "Delete Success");
    }
    return "redirect:/api/v2/task";
  }

  @GetMapping("/images")
  public String listFiles(Model model, final Pageable pageable) {
    FileListResponse list = fileService.list(pageable);
    model.addAttribute("lists", list.getData());
    model.addAttribute("message", "Upload Success");
    return "fileUpload";
  }
  

  @PostMapping("/image")
  @ResponseStatus(code = HttpStatus.CREATED)
  public String upload(@RequestParam("file") final MultipartFile file) {
    fileService.upload(FileHelper.fromMultipartFile(file));
    return "redirect:/api/v2/task/images";
  }

  @GetMapping("/images/{id}")
  public ResponseEntity<Resource> download(@PathVariable final long id) {
    return FileHelper.toResponseEntityAsAttachment(fileService.download(GENERIC_FILES, id));
  }
}
