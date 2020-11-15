package no.aaron.todoapp;

public class TaskNotFoundException extends RuntimeException {
    TaskNotFoundException(Long id) {
        super("Could not find Task " + id);
    }
}
