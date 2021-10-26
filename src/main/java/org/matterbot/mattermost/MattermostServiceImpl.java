package org.matterbot.mattermost;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.matterbot.mattermost.types.Attachments;
import org.matterbot.mattermost.types.MattermostAttachmentMessage;
import org.matterbot.mattermost.types.MattermostHookData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

@Slf4j
@Service
public class MattermostServiceImpl implements MattermostService {
    private final String iconUrl;
    private final String name;
    private final MattermostInHookClient mattermostInHookClient;
    private final String hookId;

    @Autowired
    public MattermostServiceImpl(final MattermostInHookClient mattermostInHookClient,
                                 final @NotEmpty @NotBlank @Value("${mattermost.client.hook}") String hookId,
                                 final @NotEmpty @Value("${client.icon}") String iconUrl,
                                 final @NotEmpty @Value("${client.name}") String name) {
        this.mattermostInHookClient = mattermostInHookClient;
        this.hookId = hookId;
        this.iconUrl = iconUrl;
        this.name = name;
    }

    @Override
    public ResponseEntity<String> sendMessage(final String text) throws IOException {
        MattermostHookData request = MattermostHookData.builder()
                                                       .icon_url(iconUrl)
                                                       .username(name)
                                                       .text(URLDecoder.decode(text, StandardCharsets.UTF_8))
                                                       .build();

        log.info("REQUEST: " + request.toString());
        Call<String> call = mattermostInHookClient.sendMessage(hookId, request);

        Response<String> callResult = call.execute();
        if (callResult.isSuccessful() && callResult.body() != null) {
            return ResponseEntity.ok(callResult.body());
        } else {
            log.error("STATUS: {}, BODY: {}", callResult.code(), callResult.errorBody().string());
            throw new IOException("Failed");
        }
    }

    @Override
    public ResponseEntity<String> sendImage(final String url, final String query) throws IOException {
        var request = MattermostAttachmentMessage.builder()
                                                 .icon_url(iconUrl)
                                                 .username(name)
                                                 .message(URLDecoder.decode(url, StandardCharsets.UTF_8))
                                                 .attachments(List.of(Attachments.builder()
                                                                                 .title(query)
                                                                                 .title_link(url)
                                                                                 .text(url)
                                                                                 .image_url(url)
                                                                                 .build()))
                                                 .build();

        log.info("REQUEST: " + request.toString());
        Call<String> call = mattermostInHookClient.sendAttachmentMessage(hookId, request);

        var callResult = call.execute();
        if (callResult.isSuccessful() && callResult.body() != null) {
            return ResponseEntity.ok(callResult.body());
        } else {
            log.error("STATUS: {}, BODY: {}", callResult.code(), callResult.errorBody().string());
            throw new IOException("Failed");
        }
    }

    @Override
    public ResponseEntity<String> sendMessage(final MattermostMessage mattermostMessage) throws IOException {
        return sendMessage(mattermostMessage.getMessage());
    }
}
