package com.telusko.controller;

import com.telusko.pojo.Client;
import com.telusko.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    private ClientService clientService;

    @RequestMapping(value = "/createClient", method = RequestMethod.GET)
    public String create( @ModelAttribute("client") Client client) {
        return "client/createclient";
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
            return "client/createclient";
        }else{
            clientService.create(client,model);
        }
        return "client/temp";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewClient(@ModelAttribute("client") Client client) {
        return "client/showclient";
    }

    @RequestMapping(value="/retrieve",method = RequestMethod.GET)
    public String read(@Valid @ModelAttribute("client") Client client, BindingResult result,Model model){
        if(result.hasErrors()){
            return "client/demo";
        }
        else{
            clientService.retrieve(client,model);
        }
        return "client/demo";
    }

    @RequestMapping(value="/retrieveAll",method = RequestMethod.GET)
    public String readAll(@Valid @ModelAttribute("client") Client client, BindingResult result,Model model){

        if(result.hasErrors()){
            return "client/demo";
        }
        else{
            clientService.retrieveAll(client,model);
        }
        return "client/demo";
    }

    @RequestMapping(value = "/updateClientPage", method = RequestMethod.GET)
    public String updatePage( @ModelAttribute("client") Client client) {
        return "client/update";
    }

    @RequestMapping(value="/updateClient",method = RequestMethod.GET)
    public String update(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("id",client.getId());
            model.addAttribute("name",client.getName());
            model.addAttribute("address",client.getAddress());
            return "client/result";
        }
        else{
            model.addAttribute("id",client.getId());
            model.addAttribute("name",client.getName());
            model.addAttribute("address",client.getAddress());
            clientService.update(client,model);
        }
        return "client/result";
    }

}
