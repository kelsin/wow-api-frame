package com.valefor.wowapiframe;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.valefor.wowapiframe.model.RealmList;
import com.valefor.wowapiframe.model.Character;

@Controller
public class CharactersController {

    private RealmList realmList;
    public void setRealmList(RealmList rl) { this.realmList = rl; }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView get() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("realms", realmList.all());
        return mav;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView post(HttpServletRequest request) {
        return new ModelAndView(new RedirectView("/character/" + request.getParameter("realm") + "/" + request.getParameter("character")));
    }

    @RequestMapping(value="/character/{realm}/{character}", method=RequestMethod.GET)
    public ModelAndView character(@PathVariable String realm, @PathVariable String character) {
        ModelAndView mav = new ModelAndView("character");
        mav.addObject("realm", this.realmList.findBySlug(realm));
        mav.addObject("character", Character.load(realm, character));
        return mav;
    }
}
