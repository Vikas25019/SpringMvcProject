package com.telusko.controller;

import com.telusko.pojo.Client;
import com.telusko.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    @Qualifier("clientImpl")
    private IClientService iClientService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String sayHello(@ModelAttribute("client") Client client) {
        return "createclient";
    }

    @RequestMapping(value = "/saveClient", method = RequestMethod.GET)
    public String save(@Valid @ModelAttribute("client") Client client, BindingResult result,Model model) {

        model.addAttribute("model",model);
        if (result.hasErrors()) {
            return "createclient";
        }else{
            iClientService.create(client,model);
        }
        return "demo";
    }
}
