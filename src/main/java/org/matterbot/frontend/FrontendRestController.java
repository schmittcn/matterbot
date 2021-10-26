package org.matterbot.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public interface FrontendRestController {
    @GetMapping("/")
    String jsIndex(Model model);

    @GetMapping("/vue")
    String vueIndex(Model model);
}
