package ma.formation.jackson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
@Slf4j
public class JacksonApplication {

  public static final ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) {
    SpringApplication.run(JacksonApplication.class, args);

    /**
     * L'ExecutorService
     * est une abstraction java qui permet la gestion des threads
     *
     * au lieu de gerer les threads manuellement (instanciation, demarrage, etc)
     * on delegue ca au ExecutorService
     */
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    /**
     * Runnable est une interface fonctionnelle
     * qui represente une tache executee par un thread
     *
     * apres, on va appeller le ExectorService (gestionnaire des threads)
     * pour executer ce Runnable task
     */
    Runnable task1 = () -> {
      try {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(new Person("TEST1", 27));
        log.info("JSON produit pour le 1er thread : {}", json);

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };

    Runnable task2 = () -> {
      try {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(new Person("TEST2", 59));
        log.info("JSON produit pour le 2eme thread : {}", json);

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };

    executorService.submit(task1);
    executorService.submit(task2);

    executorService.shutdown();

  }

  static class Person {

    public String name;

    public int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

  }

}
