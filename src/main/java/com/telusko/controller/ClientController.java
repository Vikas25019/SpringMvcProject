package com.telusko.controller;

import com.telusko.pojo.Client;
import com.telusko.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    @Qualifier("clientImpl")
    private ClientService clientService;

    @RequestMapping(value = "/createClient", method = RequestMethod.GET)
    public String create( @ModelAttribute("client") Client client) {
        return "client/createclient";
    }

    @RequestMapping(value = "/saveClient", method = RequestMethod.GET)
    public String save(@ModelAttribute("client") Client client, Model model) {
       String message = clientService.create(client,model);
       model.addAttribute("message",message);
       return "client/createclient";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewClient(@ModelAttribute("client") Client client) {
        return "client/showclient";
    }

    @RequestMapping(value="/retrieve",method = RequestMethod.GET)
    public String read(@ModelAttribute("client") Client client,Model model){
        String path = "client/showclient";
        String message = clientService.retrieve(client,model);
        model.addAttribute("message",message);
        boolean success = (boolean) model.getAttribute("success");
        if(success){
            path = "client/viewclient";
        }
        return path;
    }

    @RequestMapping(value="/retrieveAll",method = RequestMethod.GET)
    public String readAll(@ModelAttribute("client") Client client,Model model){
        clientService.retrieveAll(client,model);
        return "client/showAll";
    }

    @RequestMapping(value = "/updateClientPage", method = RequestMethod.GET)
    public String updatePage( @ModelAttribute("client") Client client) {
        return "client/update";
    }

    @RequestMapping(value="/updateClient",method = RequestMethod.GET)
    public String update(@ModelAttribute("client") Client client, Model model){
        String path = "client/update";
        String message = clientService.update(client,model);
        model.addAttribute("message",message);
        boolean success = (boolean) model.getAttribute("success");
        if(success){
            path = "redirect:retrieveAll";
        }
        return path;
    }

    @RequestMapping(value="/deleteClient",method = RequestMethod.GET)
    public String delete(@ModelAttribute("client") Client client, Model model,HttpServletRequest request){
        clientService.delete(client,model,request);
        return "redirect:retrieveAll";
    }

}
