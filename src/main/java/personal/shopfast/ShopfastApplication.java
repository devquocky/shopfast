package personal.shopfast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ShopfastApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopfastApplication.class, args);
    }

}
