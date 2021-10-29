package org.matterbot.services.txt2ascii;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public interface TxtToAsciiController {

    @GetMapping("/call/ascii/search")
    ResponseEntity<String> postTxtToAsciiResult(@RequestParam("query") String query) throws IOException;
}
