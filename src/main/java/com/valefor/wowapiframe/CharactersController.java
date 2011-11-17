package com.valefor.wowapiframe;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.valefor.wowapiframe.model.RealmList;

@Controller
public class CharactersController {

    private RealmList realmList;
    public void setRealmList(RealmList rl) { this.realmList = rl; }

    @RequestMapping("/")
    public ModelAndView get() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("realms", realmList.all());
        return mav;
    }

    @RequestMapping("/submit")
    public ModelAndView submit(HttpServletRequest request) {
        return new ModelAndView(new RedirectView("/" + request.getParameter("realm") + "/" + request.getParameter("character")));
    }        
}
