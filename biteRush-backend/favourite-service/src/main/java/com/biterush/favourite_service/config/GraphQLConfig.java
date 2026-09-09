package com.biterush.favourite_service.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wirinngBuilder -> wirinngBuilder
                .scalar(ExtendedScalars.UUID)
                .scalar(ExtendedScalars.DateTime);
    }

}
