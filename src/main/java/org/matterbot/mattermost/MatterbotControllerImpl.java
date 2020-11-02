package org.matterbot.mattermost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MatterbotControllerImpl implements MatterbotController {
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private MatterbotControllerImpl(final MattermostServiceImpl mattermostServiceImpl) {
        this.mattermostServiceImpl = mattermostServiceImpl;
    }

    @Override
    public ResponseEntity<String> postMessage(final String text) throws IOException {
        return mattermostServiceImpl.sendMessage(text);
    }
}
