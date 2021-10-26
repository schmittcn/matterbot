package org.matterbot.services.giphy.configuration;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.matterbot.services.giphy.GiphyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Data
@Configuration
public class GiphyConfig {
    @NotNull
    @NotEmpty
    @Value("${giphy.client.apiurl}")
    private String apiurl;

    @Bean
    public GiphyClient getGiphyClient(final Retrofit.Builder retroBuilder) {
        return retroBuilder
                .baseUrl(apiurl)
                .build()
                .create(GiphyClient.class);
    }
}
