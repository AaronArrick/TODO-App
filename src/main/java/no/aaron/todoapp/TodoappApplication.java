package no.aaron.todoapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.util.stream.Stream;

@SpringBootApplication
public class TodoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CategoryRepository repository) {
		String[] data = {
				"Test1",
				"Test2",
				"Test3"
		};

		return args -> {
			Stream.of(data).forEach(name -> {
				Category category = new Category(name);
				repository.save(category);
			});

			repository.findAll().forEach(System.out::println);
		};
	}
}
