package com.telusko.controller;

import com.telusko.pojo.Client;
import com.telusko.service.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    @Qualifier("clientImpl")
    private ICRUDService iClientService;

    @RequestMapping(value = "/createClient", method = RequestMethod.GET)
    public String create( @ModelAttribute("client") Client client) {
        return "createclient";
    }

    @RequestMapping(value = "/saveClient", method = RequestMethod.GET)
    public String save(@ModelAttribute("client") @Valid Client client, BindingResult result, Model model) {

        model.addAttribute("model",result);
        if (result.hasErrors()) {
            List<FieldError> listError=result.getFieldErrors();
            List<String> list = new ArrayList<>();
            for(FieldError error: listError){
                list.add(error.getDefaultMessage());

            }
            model.addAttribute("listError",list);
            return "createclient";
        }else{
            iClientService.create(client,model);
        }
        return "demo";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewClient(@ModelAttribute("client") Client client) {
        return "showclient";
    }

    @RequestMapping(value="/retrieve",method = RequestMethod.GET)
    public String read(@Valid @ModelAttribute("client") Client client, BindingResult result,Model model){
        String id = client.getId();
        String name = client.getName();
        String address = client.getAddress();
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        model.addAttribute("address",address);
        if(result.hasErrors()){

            return "demo";
        }
        else{
            model.addAttribute("yes","yes");
            iClientService.retrieve(client,model);
        }
        return "demo";
    }

    @RequestMapping(value="/retrieveAll",method = RequestMethod.GET)
    public String readAll(@Valid @ModelAttribute("client") Client client, BindingResult result,Model model){

        if(result.hasErrors()){
            return "demo";
        }
        else{
            model.addAttribute("yes","yes");
            iClientService.retrieveAll(client,model);
        }
        return "demo";
    }

}
