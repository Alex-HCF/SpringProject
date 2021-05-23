package org.example.configuration;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.example.utils.geoTools.DaDataGeoTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    public Mapper dozerBeanMapper(){
        return DozerBeanMapperBuilder.buildDefault();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaDataGeoTool geoTool(@Value("${dadataAuthorization}") String auth, @Value("${dadataXSecret}") String xSecret){
        return new DaDataGeoTool(auth, xSecret);
    }

}
