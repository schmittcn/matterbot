package org.matterbot.mattermost.configuration;

import lombok.Data;
import org.matterbot.mattermost.MattermostInHookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Configuration
public class MattermostConfig {
    private String apiurl;

    @Autowired
    public MattermostConfig(@NotNull @NotEmpty @Value("${mattermost.client.apiurl}") final String apiurl) {
        this.apiurl = apiurl;
    }

    @Bean
    public MattermostInHookClient getMattermostInHookClient(final Retrofit.Builder retroBuilder) {
        return retroBuilder
                .baseUrl(apiurl)
                .build()
                .create(MattermostInHookClient.class);
    }
}
