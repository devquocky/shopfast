package personal.shopfast.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class AppConfig {

//    @Value("${minio.access.name}")
//    String accessKey;
//    @Value("${minio.access.secret}")
//    String accessSecret;
//    @Value("${minio.url}")
//    String minioUrl;
//
//    @Bean
//    public MinioClient generateMinioClient() {
//        try {
//            MinioClient client = new MinioClient.Builder()
//                    .endpoint(minioUrl)
//                    .credentials(accessKey, accessSecret)
//                    .build();
//            return client;
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }


    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
        System.out.println("Date in Asia:" + new Date().toString());
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
