package no.aaron.todoapp;

public class CategoryNotFoundException extends RuntimeException {
    CategoryNotFoundException(Long id) {
        super("Could not find Category " + id);
    }
}
