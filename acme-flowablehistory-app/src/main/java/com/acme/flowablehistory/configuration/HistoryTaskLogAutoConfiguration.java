package com.acme.flowablehistory.configuration;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ievgenii Bespal
 */

@Configuration
public class HistoryTaskLogAutoConfiguration {

    @Bean
    @ConditionalOnClass(SpringProcessEngineConfiguration.class)
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> customizeSpringProcessEngineConfiguration() {
        return processEngineConfiguration -> {
            processEngineConfiguration.setEnableHistoricTaskLogging(true);
        };
    }
}
