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
     * l'ExecutorService
     * est une abstraction java qui permet de gerer les threads
     *
     * au lieu de gerer les threads manuellement (instanciation, demmarage, etc)
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
    Runnable task = () -> {
      try {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(new Person("Abdel", 27));
        log.info("JSON produit pour le 1er thread : {}", json);

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };

    Runnable task2 = () -> {
      try {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(new Person("Omar", 59));
        log.info("JSON produit pour le 2ème thread : {}", json);

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };

    executorService.submit(task);
    executorService.submit(task2);

    executorService.shutdown();

    /*long start = System.currentTimeMillis();
    
    *//*
                                            * INITIATION A LA BIBLIOTHEQUE JACKSON
                                            * 1- EN OjectMapper CLASSQIUE
                                            *//*
                                                                                    Runtime runtime = Runtime.getRuntime();
                                                                                    long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                                                                                    ObjectMapper mapper = new ObjectMapper();
                                                                                    long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                                                                  
                                                                                    System.out.println("memoire consomee apres instanciation de objectmapper" + (memoryAfter - memoryBefore));
                                                                                    JsonNode root = mapper.readTree(new File("C:\\Users\\oulabacha\\Downloads\\hp.json"));
                                                                  
                                                                                    *//*
                                                                                                                            * EN UTILISANT JsonParser BAS NIVEAU
                                                                                                                            * LA IL FAUT CREER UN JsonFactory ET L'UTILISER POUR CREER UN JsonParer SUR UN JSON FILE
                                                                                                                            *//*
                                                                                                                                                                    JsonFactory factory = new JsonFactory();
                                                                                                                                                                    List<Node> nodes = new ArrayList<Node>();
                                                                                                                                                                    try (JsonParser parser = factory.createParser(new File("C:\\Users\\oulabacha\\Downloads\\hp.json"))) {
                                                                                                                                                  
                                                                                                                                                                    while (!parser.isClosed()) {
                                                                                                                                                                     *//*
                                                                                                                                                                                                             * CHAQUE RENTREE DANS CE BLOC REPRESENTE
                                                                                                                                                                                                             * UN TOKEN
                                                                                                                                                                                                             *//*
                                                                                                                                                                                                                                                     JsonToken token = parser.nextToken();
                                                                                                                                                                                                                                                     if (token == null)
                                                                                                                                                                                                                                                     break;
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                                     if (token == JsonToken.PROPERTY_NAME && parser.currentName().equals("nodes")) {
                                                                                                                                                                                                                                                     parser.nextToken();
                                                                                                                                                                                                                                                     parser.nextToken();
                                                                                                                                                                                                                                                     MappingIterator<Node> it = mapper.readerFor(Node.class).readValues(parser);
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                                     while (it.hasNext()) {
                                                                                                                                                                                                                                                      nodes.add(it.next());
                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                     long end = System.currentTimeMillis();
                                                                                                                                                                                                                                                     System.out.println(nodes.size());
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                                     System.out.println("Time taken: " + (end - start) + "millisecondes");
                                                                                                                                                                                                                                                     }*/
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
