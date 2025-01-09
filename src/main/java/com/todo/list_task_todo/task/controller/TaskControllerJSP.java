package com.todo.list_task_todo.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todo.list_task_todo.task.dto.TaskCreateRequest;
import com.todo.list_task_todo.task.dto.TaskListResponse;
import com.todo.list_task_todo.task.service.TaskService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v2/task")
@RequiredArgsConstructor
public class TaskControllerJSP {
  private final TaskService taskService;

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
    redirectAttributes.addFlashAttribute("message", "Task created successfully");
    return "redirect:/api/v2/task";
  }

//  @PostMapping("/{id}")
//  public String update(@PathVariable final long id, RedirectAttributes redirectAttributes) {
//    if (taskService.patchCompleted(id).getCompleted()){
//      redirectAttributes.addFlashAttribute("message", "Task not completed");
//      return "redirect:/ViewToDoList";
//    }
//    redirectAttributes.addFlashAttribute("message", "Task completed");
//    return "redirect:/ViewToDoList";
//  }

  @PatchMapping("/{id}")
  public String patchCompleted(@PathVariable final long id, RedirectAttributes redirectAttributes) {
    if (taskService.patchCompleted(id).getCompleted()) {
      redirectAttributes.addFlashAttribute("message", "Task not completed");
      return "redirect:/ViewToDoList";
    }
    redirectAttributes.addFlashAttribute("message", "Task completed");
    return "redirect:/ViewToDoList";
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final long id) {
    taskService.delete(id);
  }

  @GetMapping("/test")
  public String test() {
    return "TestPage";
  }
}
