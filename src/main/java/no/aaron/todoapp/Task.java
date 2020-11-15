package no.aaron.todoapp;

import javax.persistence.*;
import java.util.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String content;

    @ManyToMany
    @JoinTable(
            name = "task_categories",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> mappedCategories;

    protected Task() {}

    public Task(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void addCategory(Category category) {
        mappedCategories.add(category);
    }

    public void removeCategory(Category category) {
        mappedCategories.remove(category);
    }

    public List<Category> getCategories() {
        return mappedCategories;
    }
}
