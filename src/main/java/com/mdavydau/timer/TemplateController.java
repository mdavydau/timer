package com.mdavydau.timer;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mdavydau
 * @date 2019-03-21
 */
@Controller
public class TemplateController {

    @GetMapping("/")
    public String time(Model model) {
        model.addAttribute("date", DateTime.now().getMillis());
        return "time";
    }

}
