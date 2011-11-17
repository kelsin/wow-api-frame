package com.valefor.wowapiframe;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.valefor.wowapiframe.model.RealmList;

@Controller
public class CharactersController {

    private RealmList realmList;
    public void setRealmList(RealmList rl) { this.realmList = rl; }

    @RequestMapping("/")
    public ModelAndView get() {
        String name = "Chris";
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("name", name);
        mav.addObject("realms", realmList.all());
        return mav;
    }
}
