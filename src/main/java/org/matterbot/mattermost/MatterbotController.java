package org.matterbot.mattermost;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface MatterbotController {
    @GetMapping("/call")
    ResponseEntity<String> postMessage(@RequestParam("text") String text) throws IOException;
}
