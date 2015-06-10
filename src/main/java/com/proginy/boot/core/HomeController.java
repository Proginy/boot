package com.proginy.boot.core;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{

    @RequestMapping(value = { "/", "home" })
    public String getHome(Map<String, Object> model)
    {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());

        return "home";
    }

}
