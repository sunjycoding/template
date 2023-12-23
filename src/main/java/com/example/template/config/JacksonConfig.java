package com.example.template.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * @author created by sunjy on 12/22/23
 */
@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            DateTimeFormatter localDateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter localDatePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            builder.serializers(new LocalDateTimeSerializer(localDateTimePattern));
            builder.serializers(new LocalDateSerializer(localDatePattern));
            builder.deserializers(new LocalDateTimeDeserializer(localDateTimePattern));
            builder.deserializers(new LocalDateDeserializer(localDatePattern));
        };
    }

}
