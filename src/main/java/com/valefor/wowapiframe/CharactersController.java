package com.valefor.wowapiframe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CharactersController {
    @RequestMapping("/")
    public String get() {
        return "index";
    }
}
