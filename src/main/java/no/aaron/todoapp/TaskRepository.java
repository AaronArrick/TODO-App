package no.aaron.todoapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAll();
    Task findById(long id);
    List<Task> findByMappedCategories(Category category);
}
