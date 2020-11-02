package org.matterbot.mattermost;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface MattermostService {
    ResponseEntity<String> sendMessage(String text) throws IOException;

    ResponseEntity<String> sendImage(String url, String query) throws IOException;

    ResponseEntity<String> sendMessage(MattermostMessage mattermostMessage) throws IOException;
}
