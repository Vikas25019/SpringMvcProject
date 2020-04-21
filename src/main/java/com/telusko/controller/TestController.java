package com.telusko.controller;

import com.telusko.pojo.Client;
import com.telusko.service.ICRUDService;
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

@Controller
public class TestController {

    @Autowired
    @Qualifier("clientImpl")
    private ICRUDService iClientService;

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

    @RequestMapping(value="/retrieve",method = RequestMethod.GET)
    public String read(@Valid @ModelAttribute("client") Client client, BindingResult result,Model model){
        if(result.hasErrors()){
            return "showclient";
        }
        else{
            iClientService.retrieve(client,model);
        }
        return "demo";
    }


}
