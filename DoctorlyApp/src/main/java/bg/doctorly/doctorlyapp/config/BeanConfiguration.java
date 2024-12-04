package bg.doctorly.doctorlyapp.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class BeanConfiguration {



    @Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<LocalDateTime, String> converterDateToString = new Converter<LocalDateTime, String>() {
            @Override
            public String convert(MappingContext<LocalDateTime, String> context) {
                return DateTimeFormatter.ofPattern("d MMMM yyyy 'at' HH:mm").format(context.getSource());
            }
        };

        Converter<String, LocalDateTime> convertToTime = new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> context) {
                return LocalDateTime.parse(context.getSource(), DateTimeFormatter.ISO_DATE_TIME);
            }
        };

        modelMapper.addConverter(converterDateToString);
        modelMapper.addConverter(convertToTime);
        return modelMapper;
    }


}
