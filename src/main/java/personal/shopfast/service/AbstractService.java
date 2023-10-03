package personal.shopfast.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import personal.shopfast.util.ObjectValidator;

import javax.annotation.PostConstruct;

public abstract class AbstractService {
    @Autowired
    protected Environment env;

    protected ObjectMapper objectMapper;

    @Autowired
    protected ObjectValidator objectValidator;

    @PostConstruct
    public void init(){
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }
}
