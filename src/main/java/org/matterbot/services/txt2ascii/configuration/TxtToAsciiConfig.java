package org.matterbot.services.txt2ascii.configuration;

import lombok.Data;
import org.matterbot.services.txt2ascii.TxtToAsciiClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Configuration
@ConfigurationProperties(prefix = "txt2ascii.client")
public class TxtToAsciiConfig {
    @NotNull
    @NotEmpty
    private String apiurl;

    @Bean
    public TxtToAsciiClient getTxtToAsciiClient(final Retrofit.Builder retroBuilder) {
        return retroBuilder
                .baseUrl(apiurl)
                .build()
                .create(TxtToAsciiClient.class);
    }
}
