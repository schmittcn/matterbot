package org.matterbot.mattermost;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public interface MatterbotController {
    @RequestMapping(method = RequestMethod.GET, value = {"/call"})
    ResponseEntity<String> postMessage(@RequestParam("text") String text) throws IOException;
}
