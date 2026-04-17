package com.hospital.appointment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Registers a @LoadBalanced RestTemplate bean.
 *
 * @LoadBalanced intercepts every HTTP call made with this RestTemplate
 * and resolves the hostname against the Eureka registry.
 * e.g.  "http://PATIENT-SERVICE/api/patients"
 *         → Eureka looks up PATIENT-SERVICE → returns localhost:8081
 *         → request goes to http://localhost:8081/api/patients
 *
 * This means: changing a service's port requires NO code change here —
 * Eureka handles discovery automatically.
 *
 * JavaTimeModule is also configured so LocalDate fields in DTOs
 * deserialize correctly from the JSON responses.
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced   // ← enables Eureka service-name resolution
    public RestTemplate restTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                    .removeIf(c -> c instanceof MappingJackson2HttpMessageConverter);
        restTemplate.getMessageConverters().add(0, converter);

        return restTemplate;
    }
}
