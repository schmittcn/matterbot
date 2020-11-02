package org.matterbot.services.urbandictionary.configuration;

import lombok.Data;
import org.matterbot.services.urbandictionary.UrbanDictionaryClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Configuration
@ConfigurationProperties(prefix = "urbandictionary.client")
public class UrbanDictionaryConfig {
    @NotNull
    @NotEmpty
    private String apiurl;

    @Bean
    public UrbanDictionaryClient getUrbanDictionaryClient(final Retrofit.Builder retroBuilder) {
        return retroBuilder
                .baseUrl(apiurl)
                .build()
                .create(UrbanDictionaryClient.class);
    }
}
