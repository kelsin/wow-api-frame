package com.valefor.wowapiframe;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class CharactersController {
    @RequestMapping("/")
    public ModelAndView get() {
        String name = "Chris";
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("name", name);
        return mav;
    }
}
