package API_Demo.Springboot.Database;


import API_Demo.Springboot.Models.Product;
import API_Demo.Springboot.Repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class database {
    private static final Logger logger = LoggerFactory.getLogger(database.class);
    @Bean
    //create in-memory database
    CommandLineRunner initDatabase(ProductRepository productRepository)
    {
         return new CommandLineRunner() {
             @Override
             public void run(String... args) throws Exception {
                 Product productA = new Product("Macbook Pro 16", 2020, 2400.0, "");
                 Product productB = new Product("Ipad Air Green", 2021, 599.0, "");
                 logger.info("insert data: "+productRepository.save(productA));
                 logger.info("insert data: "+productRepository.save(productB));
             }
         };
    }
}
