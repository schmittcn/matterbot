package org.matterbot.frontend;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class FrontendRestControllerImpl implements FrontendRestController {
    @Override
    public String jsIndex(final Model model) {
        return "js/index";
    }

    @Override
    public String vueIndex(final Model model) {
        return "vue/index";
    }
}
