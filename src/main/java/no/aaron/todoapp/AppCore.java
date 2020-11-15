package no.aaron.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppCore {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        for (Task task : taskRepository.findByMappedCategories(category)) {
            removeTaskCategory(task.getId(), id);
        }

        categoryRepository.deleteById(id);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/filter/{categoryid}")
    public List<Task> getAllTasksByCategory(@PathVariable Long categoryid) {
        Category category = categoryRepository.findById(categoryid).orElseThrow(() -> new CategoryNotFoundException(categoryid));

        return taskRepository.findByMappedCategories(category);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable Long id) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @PatchMapping("/tasks/{taskid}/{categoryid}")
    public void addTaskCategory(@PathVariable Long taskid, @PathVariable Long categoryid) {
        Task task = taskRepository.findById(taskid).orElseThrow(() -> new TaskNotFoundException(taskid));
        Category category = categoryRepository.findById(categoryid).orElseThrow(() -> new CategoryNotFoundException(categoryid));

        task.addCategory(category);
        taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{taskid}/{categoryid}")
    public void removeTaskCategory(@PathVariable Long taskid, @PathVariable Long categoryid) {
        Task task = taskRepository.findById(taskid).orElseThrow(() -> new TaskNotFoundException(taskid));
        Category category = categoryRepository.findById(categoryid).orElseThrow(() -> new CategoryNotFoundException(categoryid));

        task.removeCategory(category);
        taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
